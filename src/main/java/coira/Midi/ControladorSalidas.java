/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.Midi;

import coira.guitarra.ordenes.EnumeradoOrdenes;
import coira.properties.GeneralProperties;
import coira.properties.GuitarProperties;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class ControladorSalidas {
    
    private boolean puertoRotativo = false;
    private boolean allPorts = false;
    private int cantidadPuertos = 3;
    private int ultimoPuerto = -10;
    private HashMap<Integer,Integer> cuerdas = new HashMap <Integer, Integer>();
    private HashMap<Integer,Integer> puertos = new HashMap <Integer, Integer>();
    
    private GeneralProperties generalProperties = null;
    private GuitarProperties gp =null; 

    public ControladorSalidas(GuitarProperties gp,GeneralProperties generalProperties) {
        this.gp = gp;
        this.generalProperties = generalProperties;
        puertoRotativo = gp.isPort_signal_sending_rotative();
        allPorts = gp.isPort_signal_sending_all();
        cantidadPuertos = gp.getPort_signal_sending_quantity();
    }

    public String obtenerPuerto(int nroCuerda, EnumeradoOrdenes orden){
        String resultado = "default";
        if  (!puertoRotativo){
            resultado = generalProperties.getPropertyAsString("midi.port."+nroCuerda);
            
        }else{
            
            Integer canalCuerda = cuerdas.get(nroCuerda);
            if (canalCuerda == null){
                //la cuerda no se envio todavia a ningun puerto
                ultimoPuerto = (ultimoPuerto == -10 || ultimoPuerto >= cantidadPuertos) ? 1 : ultimoPuerto+1;
                resultado = generalProperties.getPropertyAsString("midi.port."+ultimoPuerto);
                cuerdas.put(nroCuerda, ultimoPuerto);
                puertos.put(ultimoPuerto,nroCuerda);
            }else{
                Integer cuerdaCanal = puertos.get(canalCuerda);
                if (cuerdaCanal == nroCuerda || orden.equals(EnumeradoOrdenes.BEND_OFF) || orden.equals(EnumeradoOrdenes.CORTE) || orden.equals(EnumeradoOrdenes.SLIDE_DOWN_OFF) || orden.equals(EnumeradoOrdenes.SLIDE_UP_OFF)|| orden.equals(EnumeradoOrdenes.SLIDE_OFF)){
                    //fue la ultima cuerda del canal
                    resultado = generalProperties.getPropertyAsString("midi.port."+canalCuerda);
                }else{
                    ultimoPuerto = (ultimoPuerto == -10 || ultimoPuerto >= cantidadPuertos) ? 1 : ultimoPuerto+1;
                    resultado = generalProperties.getPropertyAsString("midi.port."+ultimoPuerto);
                    cuerdas.put(nroCuerda, ultimoPuerto);
                    puertos.put(ultimoPuerto,nroCuerda);
                }
            }
        }
        return resultado;
    }
    
    
}
