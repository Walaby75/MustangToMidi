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
    private int max_Strength, min_Strength;
    
    
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

    public int getMax_Strength() {
        return max_Strength;
    }

    public void setMax_Strength(int max_Strength) {
        this.max_Strength = max_Strength;
    }

    public int getMin_Strength() {
        return min_Strength;
    }

    public void setMin_Strength(int min_Strength) {
        this.min_Strength = min_Strength;
    }
    
    
    
    
    public void configurar(String archivo){
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("conf/cuerdas/"+archivo)){
            propiedades.load(fis);
            int cuerda=1;
            cuerdas = new HashMap<>();
            while (cuerda < 11){
                if (propiedades.getProperty("Cuerda"+cuerda)!=null){
                    cuerdas.put(cuerda, new DataCFGCuerda(Util.noteToMidi(propiedades.getProperty("Cuerda"+cuerda).trim()),propiedades.getProperty("Modo").trim(),Integer.parseInt(propiedades.getProperty("Tramos").trim())));
                }
                cuerda=cuerda+1;
            }
            try{
                max_Strength = Integer.parseInt(propiedades.getProperty("max"));
            }catch (Exception e){
                max_Strength = 128;
            }
            try{
                min_Strength = Integer.parseInt(propiedades.getProperty("min"));
            }catch (Exception e){
                min_Strength = -10;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
