/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VST;

import coira.Midi.ListaMidiOrdenada;
import coira.Midi.OrdenMidi2025;
import coira.guitarra.Cuerda;
import coira.util.Util;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author Usuario
 */
public class Ample extends VSTGuitarras{

    @Override
    public ListaMidiOrdenada slideUp(Integer cuerda, Integer origen, Integer destino, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.slideUp(cuerda, origen, destino, fuerza, cuerdaObjeto);
        int trigger;
        trigger = Util.noteToMidi(propiedades.getProperty("trigger_slide", "0"));
        // agregar órdenes particulares de Ample aquí
        lista.add(new OrdenMidi2025(50,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,trigger,fuerza,0));
        lista.add(new OrdenMidi2025(250,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,trigger,fuerza,0));
        return lista;
    }

    @Override
    public ListaMidiOrdenada slideDown(Integer cuerda, Integer origen, Integer destino, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.slideDown(cuerda, origen, destino, fuerza, cuerdaObjeto);
        int trigger = Util.noteToMidi(propiedades.getProperty("trigger_slide", "0"));
        // agregar órdenes particulares de Ample aquí
        lista.add(new OrdenMidi2025(50,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,trigger,fuerza,0));
        lista.add(new OrdenMidi2025(250,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,trigger,fuerza,0));

        // agregar comportamiento propio
        return lista;
    }

    @Override
    public ListaMidiOrdenada hammerOn(Integer cuerda, Integer origen, Integer destino, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.hammerOn(cuerda, origen, destino, fuerza, cuerdaObjeto);
        int trigger = Util.noteToMidi(propiedades.getProperty("trigger_hammer_on", "0"));
        // agregar órdenes particulares de Ample aquí
        lista.add(new OrdenMidi2025(50,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,trigger,fuerza,0));
        lista.add(new OrdenMidi2025(250,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,trigger,fuerza,0));
        
        // comportamiento particular
        return lista;
    }

    @Override
    public ListaMidiOrdenada pullOff(Integer cuerda, Integer origen, Integer destino, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.pullOff(cuerda, origen, destino, fuerza, cuerdaObjeto);
        int trigger = Util.noteToMidi(propiedades.getProperty("trigger_pull_off", "0"));
        // agregar órdenes particulares de Ample aquí
        lista.add(new OrdenMidi2025(50,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_ON,trigger,fuerza,0));
        lista.add(new OrdenMidi2025(250,cuerdaObjeto,cuerdaObjeto.getPuerto(),cuerdaObjeto.getCanal(),ShortMessage.NOTE_OFF,trigger,fuerza,0));
        
        return lista;
    }

    @Override
    public ListaMidiOrdenada bendOn(Integer cuerda, Integer origen, Integer destino, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.bendOn(cuerda, origen, destino, fuerza, cuerdaObjeto);
        return lista;
    }

    @Override
    public ListaMidiOrdenada bendOff(Integer cuerda, Integer origen, Integer destino, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.bendOff(cuerda, origen, destino, fuerza, cuerdaObjeto);
        return lista;
    }

    @Override
    public ListaMidiOrdenada stop(Integer cuerda, Integer origen, Integer destino, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.stop(cuerda, origen, destino, fuerza, cuerdaObjeto);
        return lista;
    }

    @Override
    public ListaMidiOrdenada pullString(Integer cuerda, Integer tono, Integer tonoAnterior, Integer fuerza, Cuerda cuerdaObjeto) {
        ListaMidiOrdenada lista = super.pullString(cuerda, tono,tonoAnterior, fuerza, cuerdaObjeto);
        return lista;
    }

    @Override
    public ListaMidiOrdenada stopAll() {
        ListaMidiOrdenada lista = super.stopAll();
        return lista;
    }
    
}
