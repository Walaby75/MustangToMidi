/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coira.Midi;

import configuraciones.salida.CFGSalidas;
import configuraciones.salida.DataCFGSalida;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author Usuario
 */
public class ControladorSalidas {
    
    private static ControladorSalidas instance;
    private HashMap<String,MidiDevice> dispositivos;
    
    
    public static ControladorSalidas getInstance(){
        if (instance == null){
            instance = new ControladorSalidas();
        }
        return instance;
    }
    
    
    public void configurar(Properties propiedades){
        
    }
    
    
    public void configurar(CFGSalidas configuracion){
        dispositivos = new HashMap<String,MidiDevice>();
        for (DataCFGSalida cfg : configuracion.getSalidas().values()){
            System.out.println(cfg.getPuerto());
            dispositivos.put(cfg.getPuerto(), null);
        }    
        
        
        MidiDevice deviceLista=null;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i=0;i<infos.length;i++){
            try {
                deviceLista = MidiSystem.getMidiDevice(infos[i]);
                System.out.println(deviceLista.getDeviceInfo().getName()+" - : "+i);
                System.out.println(deviceLista.getReceivers());
                
                MidiDevice device = MidiSystem.getMidiDevice(infos[i]);
                
                String clave = deviceLista.getDeviceInfo().getName();
                if (device.getMaxReceivers()!=0 && dispositivos.containsKey(clave)){
                    System.out.println("[" + i + "] " + infos[i].getName()
            + " | Receivers: " + device.getMaxReceivers()
            + " | Transmitters: " + device.getMaxTransmitters());
                    device.open();
                    dispositivos.put(clave, device);
                }
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(ControladorSalidas.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 

        
    }
    
    public void enviar(ListaMidiOrdenada lista){
        for (OrdenMidi2025 orden : lista){
            try {
                System.out.printf("→ Enviando: %s canal=%d nota=%d fuerza=%d%n",
    orden.getComando() == ShortMessage.NOTE_ON ? "NOTE_ON" :
    orden.getComando() == ShortMessage.NOTE_OFF ? "NOTE_OFF" : "OTRO",
    orden.getCanal(), orden.getNota(), orden.getFuerza());
                System.out.println("→ Dispositivo: " + dispositivos.get(orden.getPuerto()));
                ShortMessage mensaje = new ShortMessage();
                mensaje.setMessage(orden.getComando(),orden.getCanal()-1,orden.getNota(),orden.getFuerza());
                dispositivos.get(orden.getPuerto()).getReceiver().send(mensaje, -1);
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(ControladorSalidas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(ControladorSalidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
            
}
