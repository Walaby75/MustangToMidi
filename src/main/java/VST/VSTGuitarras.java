/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VST;

import coira.Midi.ListaMidiOrdenada;
import coira.Midi.OrdenMidi2025;
import coira.guitarra.ControlCuerdas;
import coira.guitarra.Cuerda;
import java.util.Properties;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author Usuario
 */
public class VSTGuitarras {
    
    Properties propiedades;
    
    public ListaMidiOrdenada slideUp(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        lista.add(new OrdenMidi2025(100,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,destino,fuerza,0));
        lista.add(new OrdenMidi2025(200,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,origen,fuerza,0));
        // agregar órdenes comunes
        return lista;
    };
    
    public ListaMidiOrdenada slideDown(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        lista.add(new OrdenMidi2025(100,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,destino,fuerza,0));
        lista.add(new OrdenMidi2025(200,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,origen,fuerza,0));

        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada hammerOn(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        lista.add(new OrdenMidi2025(100,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,destino,fuerza,0));
        lista.add(new OrdenMidi2025(200,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,origen,fuerza,0));

        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada pullOff(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){

        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        lista.add(new OrdenMidi2025(100,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,destino,fuerza,0));
        lista.add(new OrdenMidi2025(200,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,origen,fuerza,0));

        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada bendOn(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada bendOff(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada stop(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        Cuerda c = cuerdaObjeto;
        lista.add(new OrdenMidi2025(100,c,c.getPuerto(),c.getCanal(),ShortMessage.NOTE_OFF,c.getUltimoTono(),127,0));
        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada pullString(Integer cuerda,Integer tono, Integer tonoAnterior, Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        lista.add(new OrdenMidi2025(100,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,tonoAnterior,fuerza,0));
        lista.add(new OrdenMidi2025(200,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,tono,fuerza,0));
        
        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada stopAll(){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        if (ControlCuerdas.getInstance().getCuerda_1()!= null){
            Cuerda c = ControlCuerdas.getInstance().getCuerda_1();
            lista.add(new OrdenMidi2025(100,c,c.getPuerto(),c.getCanal(),ShortMessage.NOTE_OFF,c.getUltimoTono(),127,0));
        }
        if (ControlCuerdas.getInstance().getCuerda_2()!= null){
            Cuerda c = ControlCuerdas.getInstance().getCuerda_2();
            lista.add(new OrdenMidi2025(100,c,c.getPuerto(),c.getCanal(),ShortMessage.NOTE_OFF,c.getUltimoTono(),127,0));
        }
        if (ControlCuerdas.getInstance().getCuerda_3()!= null){
            Cuerda c = ControlCuerdas.getInstance().getCuerda_3();
            lista.add(new OrdenMidi2025(100,c,c.getPuerto(),c.getCanal(),ShortMessage.NOTE_OFF,c.getUltimoTono(),127,0));
        }
        if (ControlCuerdas.getInstance().getCuerda_4()!= null){
            Cuerda c = ControlCuerdas.getInstance().getCuerda_4();
            lista.add(new OrdenMidi2025(100,c,c.getPuerto(),c.getCanal(),ShortMessage.NOTE_OFF,c.getUltimoTono(),127,0));
        }
        if (ControlCuerdas.getInstance().getCuerda_5()!= null){
            Cuerda c = ControlCuerdas.getInstance().getCuerda_5();
            lista.add(new OrdenMidi2025(100,c,c.getPuerto(),c.getCanal(),ShortMessage.NOTE_OFF,c.getUltimoTono(),127,0));
        }
        if (ControlCuerdas.getInstance().getCuerda_6()!= null){
            Cuerda c = ControlCuerdas.getInstance().getCuerda_6();
            int tonoApagar = c.getUltimoTono();
            lista.add(new OrdenMidi2025(100,c,c.getPuerto(),c.getCanal(),ShortMessage.NOTE_OFF,c.getUltimoTono(),127,0));
        }

        // agregar órdenes comunes
        return lista;
    };

    public Properties getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(Properties propiedades) {
        this.propiedades = propiedades;
    }
    
    
    
    
    
}
