/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VST;

import coira.Midi.ListaMidiOrdenada;
import coira.Midi.OrdenMidi2025;
import coira.guitarra.Cuerda;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author Usuario
 */
public class VSTGuitarras {
    
    public ListaMidiOrdenada slideUp(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        // agregar órdenes comunes
        return lista;
    };
    
    public ListaMidiOrdenada slideDown(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada hammerOn(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
        // agregar órdenes comunes
        return lista;
    };
    
    
    public ListaMidiOrdenada pullOff(Integer cuerda, Integer origen, Integer destino,Integer fuerza, Cuerda cuerdaObjeto){
        ListaMidiOrdenada lista = new ListaMidiOrdenada();
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
        // agregar órdenes comunes
        return lista;
    };
    
    
    
}
