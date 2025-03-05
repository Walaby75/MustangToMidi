/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuraciones.cuerdas;

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
public class CFGCuerdas {

    private static CFGCuerdas instance;
    private CFGCuerdas(){};
    private Map<Integer,DataCFGCuerda> cuerdas;
    
    
    public static CFGCuerdas getInstance(){
        if (instance == null){
            instance = new CFGCuerdas();
        }
        return instance;
    };

    public Map<Integer, DataCFGCuerda> getCuerdas() {
        return cuerdas;
    }

    public void setCuerdas(Map<Integer, DataCFGCuerda> cuerdas) {
        this.cuerdas = cuerdas;
    }
    
    public void configurar(String archivo){
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("conf/cuerdas/"+archivo)){
            propiedades.load(fis);
            int cuerda=1;
            cuerdas = new HashMap<>();
            while (propiedades.getProperty("Cuerda"+cuerda)!=null){
                cuerdas.put(cuerda, new DataCFGCuerda(Util.noteToMidi(propiedades.getProperty("Cuerda"+cuerda))));
                cuerda=cuerda+1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
