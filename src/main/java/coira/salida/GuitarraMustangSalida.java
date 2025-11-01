/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coira.salida;

import VST.VSTGuitarras;
import coira.guitarra.eventos.EventoTrasteCuerda;
import coira.guitarra.ordenes.OrdenGuitarra;
import coira.guitarra.ordenes.OrdenLegato;
import coira.guitarra.ordenes.OrdenPulsada;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Usuario
 */
public class GuitarraMustangSalida {
    private static GuitarraMustangSalida instance;
    
    private boolean modoLegato=false;
    private boolean muteOnchange = false;
    private VSTGuitarras vst;
    
    public static GuitarraMustangSalida getInstance(){
        if (instance == null){
            instance = new GuitarraMustangSalida();
        }
        return instance;
    }
    
    public void iniciar(String properties){
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("conf/VST/"+properties)){
            propiedades.load(fis);
            if (propiedades.getProperty("VST").equalsIgnoreCase("ample")){
                
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("generic")){
                
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("modoBass")){
                
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("realGuitar")){
                
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("vir2Acoustic")){
                
                
            }else if (propiedades.getProperty("VST").equalsIgnoreCase("vir2Electric")){
                
                
            }else {
                
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void ejecutar(OrdenGuitarra orden){
        System.out.println("Orden guitarra recibida : "+orden.getClass().getCanonicalName());
        if (orden instanceof OrdenPulsada){
           
        } else if ( orden instanceof OrdenLegato) {
            if (modoLegato){
                
            }
                
            
        }
    }

    public void ejecutar(EventoTrasteCuerda orden){
        System.out.println("Orden cuerda recibida : "+orden.getClass().getCanonicalName());
        if (muteOnchange){
            
        }
    }

    
}
