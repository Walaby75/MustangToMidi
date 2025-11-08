/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coira.salida;

import VST.Ample;
import VST.Generic;
import VST.ModoBass;
import VST.RealGuitar;
import VST.VSTGuitarras;
import VST.Vir2Acoustic;
import VST.Vir2Electric;
import coira.Midi.ControladorSalidas;
import coira.Midi.ListaMidiOrdenada;
import coira.guitarra.eventos.EventoTrasteCuerda;
import coira.guitarra.ordenes.OrdenApagado;
import coira.guitarra.ordenes.OrdenBoton1;
import coira.guitarra.ordenes.OrdenBoton2;
import coira.guitarra.ordenes.OrdenGuitarra;
import coira.guitarra.ordenes.OrdenHammerOn;
import coira.guitarra.ordenes.OrdenLegato;
import coira.guitarra.ordenes.OrdenPullOff;
import coira.guitarra.ordenes.OrdenPulsada;
import coira.guitarra.ordenes.OrdenSlideDown;
import coira.guitarra.ordenes.OrdenSlideUp;
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
                vst = new Ample();
                vst.setPropiedades(propiedades);
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("generic")){
                vst = new Generic();
                vst.setPropiedades(propiedades);
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("modoBass")){
                vst = new ModoBass();
                vst.setPropiedades(propiedades);
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("realGuitar")){
                vst = new RealGuitar();
                vst.setPropiedades(propiedades);
            } else if (propiedades.getProperty("VST").equalsIgnoreCase("vir2Acoustic")){
                vst = new Vir2Acoustic();
                vst.setPropiedades(propiedades);
                
            }else if (propiedades.getProperty("VST").equalsIgnoreCase("vir2Electric")){
                vst = new Vir2Electric();
                vst.setPropiedades(propiedades);
                
            }else {
                vst = new Generic();
                vst.setPropiedades(propiedades);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void ejecutar(OrdenGuitarra orden){
        ListaMidiOrdenada lista= new ListaMidiOrdenada(true);
        if (orden instanceof OrdenPulsada aux){
           lista = vst.pullString(aux.getCuerda().getCanal(), aux.getNota(),aux.getNotaAnterior() ,aux.getFuerza(), aux.getCuerda());
        } else if ( orden instanceof OrdenLegato) {
            if (modoLegato){
                if ( orden instanceof OrdenSlideDown aux) {
                    lista = vst.slideDown(aux.getVariacion(), aux.getTonoOrigen(), aux.getTonoDestino(), aux.getFuerza(), aux.getCuerda());
                } else if ( orden instanceof OrdenSlideUp aux) {
                    lista = vst.slideUp(aux.getVariacion(), aux.getTonoOrigen(), aux.getTonoDestino(), aux.getFuerza(), aux.getCuerda());                    
                } else if ( orden instanceof OrdenHammerOn aux) {
                    lista = vst.hammerOn(aux.getVariacion(), aux.getTonoOrigen(), aux.getTonoDestino(), aux.getFuerza(), aux.getCuerda());                    
                } else if ( orden instanceof OrdenPullOff aux) {
                    lista = vst.pullOff(aux.getVariacion(), aux.getTonoOrigen(), aux.getTonoDestino(), aux.getFuerza(), aux.getCuerda());                    
                }
                
            }
                
            
        } else if ( orden instanceof OrdenApagado) {
            lista = vst.stopAll();
        } else if ( orden instanceof OrdenBoton1 ){
            modoLegato = !modoLegato;
        } else if ( orden instanceof OrdenBoton2 ){
            muteOnchange = !muteOnchange;
        }
        ControladorSalidas.getInstance().enviar(lista);
    }

    public void ejecutar(EventoTrasteCuerda orden){
        ListaMidiOrdenada lista= new ListaMidiOrdenada(true);
        //System.out.println("Orden cuerda recibida : "+orden.getClass().getCanonicalName());
        
        if (muteOnchange && !modoLegato){
            lista = vst.stop(0, 0, 0, 0, orden.getCuerdaObjeto());
                    //slideDown(aux.getVariacion(), aux.getTonoOrigen(), aux.getTonoDestino(), aux.getFuerza(), aux.getCuerda());

        }
        ControladorSalidas.getInstance().enviar(lista);
    }

    
}
