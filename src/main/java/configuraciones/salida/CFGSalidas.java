/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuraciones.salida;

import configuraciones.cuerdas.*;
import coira.util.Util;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.AuthProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Usuario
 */
public class CFGSalidas {

    private static CFGSalidas instance;
    private CFGSalidas(){};
    private Map<Integer,DataCFGSalida> salidas;
    
    
    public static CFGSalidas getInstance(){
        if (instance == null){
            instance = new CFGSalidas();
        }
        return instance;
    };

    public Map<Integer, DataCFGSalida> getSalidas() {
        return salidas;
    }

    public void setSalidas(Map<Integer, DataCFGSalida> salidas) {
        this.salidas = salidas;
    }

    
    
    public void configurar(String archivo){
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("conf/salida/"+archivo)){
            propiedades.load(fis);
            boolean defecto = propiedades.getProperty("midi.ports").equalsIgnoreCase("default");
            int cuerda=1;
            salidas = new HashMap<>();
            while (propiedades.getProperty("midi.chanel.String."+cuerda)!=null){
                
                Integer chanel = Integer.parseInt(propiedades.getProperty("midi.chanel.String."+cuerda));
                String asignado = propiedades.getProperty("midi.port.String."+cuerda);
                String puerto =  defecto ? propiedades.getProperty("midi.port.default") : propiedades.getProperty("midi.port."+asignado);
                salidas.put(cuerda, new DataCFGSalida(puerto,chanel));
                cuerda=cuerda+1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
