/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.Midi;

import coira.guitarra.ordenes.EnumeradoOrdenes;
import coira.guitarra.ordenes.OrdenApagado;
import coira.guitarra.ordenes.OrdenApagadoCuerda;
import coira.guitarra.ordenes.OrdenApagadoCuerdaDelay;
import coira.guitarra.ordenes.OrdenBendOff;
import coira.guitarra.ordenes.OrdenBendUp;
import coira.guitarra.ordenes.OrdenBoton1;
import coira.guitarra.ordenes.OrdenCambioCuerda;
import coira.guitarra.ordenes.OrdenHammerOn;
import coira.guitarra.ordenes.OrdenPullOff;
import coira.guitarra.ordenes.OrdenPulsada;
import coira.guitarra.ordenes.OrdenPulsadaBend;
import coira.guitarra.ordenes.OrdenSlide;
import coira.guitarra.ordenes.OrdenSlideDown;
import coira.guitarra.ordenes.OrdenSlideUp;
import coira.guitarra.ordenes.OrdenStopSlide;
import coira.guitarra.ordenes.OrdenSwitchOnOffSlide;
import coira.properties.GeneralProperties;
import coira.properties.GuitarProperties;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;
import javax.sql.rowset.serial.SerialArray;


/**
 *
 * @author Administrador
 */
public class SalidaMidi implements  Observer{
    GuitarProperties guitarProperties;
    GeneralProperties generalProperties;
    //private MidiDevice device=null;
    private int[] ultimaNota = {-10,-10,-10,-10,-10,-10,-10};
    private int[] ultimaFuerza = {-10,-10,-10,-10,-10,-10,-10};
    private Date[] ultimaPulsada = {new Date(),new Date(),new Date(),new Date(),new Date(),new Date(),new Date()};
    private int slideUp = 31;
    private int slide = 0;
    private int ultimaCuerda=-1;
    private int ultimaCuerdaSeleccionada=-1;
    private int slideDown =30;
    private int bend =29;
    private int bendTones=0;
    private boolean slideBool=false;
    private ControladorSalidas controlSalida = null;
    private boolean sliding=false;
    private HashMap<Integer,Integer> cuerdasSliding = new HashMap <Integer,Integer>();
    
    //private int cuerdaSliding=-10;

    public boolean isSlideBool() {
        return slideBool;
    }

    public void setSlideBool(boolean slideBool) {
        this.slideBool = slideBool;
    }
    
    
    
    
    private HashMap<String,MidiDevice> devices = new HashMap<String,MidiDevice>();
    public SalidaMidi(GuitarProperties gp, GeneralProperties prop ) throws MidiUnavailableException {
        //Vector synthInfos = new Vector();
        generalProperties=prop;
        for (int iPort=1;iPort<=prop.getPropertyAsInt("midi.port.cant", 6);iPort++){
            devices.put(prop.getPropertyAsString("midi.port."+iPort, ""+iPort), null);
        }
        
        MidiDevice deviceLista=null;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i=10;i<infos.length;i++){
            deviceLista = MidiSystem.getMidiDevice(infos[i]);
            System.out.println(deviceLista.getDeviceInfo().getName()+" - : "+i);
            System.out.println(deviceLista.getReceivers());
            
            if(deviceLista.getDeviceInfo().getName().indexOf(prop.getPropertyAsString("midi.port.default", "LoopBe"), 0)>=0){
                devices.put("default", MidiSystem.getMidiDevice(infos[i]));
                
            }else{
                if (devices.containsKey(deviceLista.getDeviceInfo().getName())){
                    devices.put(deviceLista.getDeviceInfo().getName(), MidiSystem.getMidiDevice(infos[i]));
                }
            }
//            if (deviceLista.getDeviceInfo().getName().indexOf("myMidiPort", 0)>=0){
//                devices.add(MidiSystem.getMidiDevice(infos[i]));
//                
//            }

        } 
        this.setGuitarProperties(gp);
        
        
        for (MidiDevice md:devices.values()){
            md.open();
        }
        
       controlSalida = new ControladorSalidas(gp, generalProperties);
    }

    public void slideUp(int disp,int canal,int variacion){
        try {
            ShortMessage myMsg = new ShortMessage();
            
            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 121, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);

            //*******************este bloque marca  que sean 12 tones de pitch
            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 101, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 100, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 6, 12);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);
            
            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 101, 127);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 100, 127);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);
            
            
            
            
            
            //*******************este bloque marca  que sean 12 tones de pitch
            
            
            
            
            
            int variacionTotal = variacion*guitarProperties.getControl_slide_wheel_variation();
            for (int i = 1; i <= variacionTotal; i++) {
                myMsg.setMessage(ShortMessage.PITCH_BEND,canal-1,127,63+(i));
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);

            }

            
            
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    public void slideDown(int disp, int canal,int variacion){
            try {
            ShortMessage myMsg = new ShortMessage();

            
            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 121, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

            
            //*******************este bloque marca  que sean 12 tones de pitch
            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 101, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 100, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 6, 12);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp, EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);
            //*******************este bloque marca  que sean 12 tones de pitch
            
            int variacionTotal = variacion*guitarProperties.getControl_slide_wheel_variation();
            for (int i = -1; i >= variacionTotal; i--) {
                myMsg.setMessage(ShortMessage.PITCH_BEND,canal-1,127,64+(i));
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

            }
            
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stopSlide(int disp,int canal){
        try {
            ShortMessage myMsg = new ShortMessage();
            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 121, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_OFF)).getReceiver().send(myMsg, -1);

            myMsg.setMessage(ShortMessage.PITCH_BEND,canal-1,0,64);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_OFF)).getReceiver().send(myMsg, -1);
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop_Slide (int disp,int canal){
        try {
            ShortMessage myMsg = new ShortMessage();
            if (!guitarProperties.isControl_slide_wheel()){
                myMsg.setMessage(ShortMessage.NOTE_OFF,canal-1,slideUp,100);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_OFF)).getReceiver().send(myMsg, -1);
                
            }else{
                stopSlide(disp, canal);
            }
            


            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void start_Slide (int disp,int canal){
        try {
            ShortMessage myMsg = new ShortMessage();
            //myMsg.setMessage(ShortMessage.NOTE_OFF,canal,slideUp,100);
            //device.getReceiver().send(myMsg, -1);
            myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,slideUp,100);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_ON)).getReceiver().send(myMsg, -1);

            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    


        
    public void slideP (int disp,int canal, int nota){
        try {
            ShortMessage myMsg = new ShortMessage();
            myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,nota,ultimaFuerza[canal]);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_ON)).getReceiver().send(myMsg, -1);

            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        public void slide_Up (final int disp,final int canal, final int nota, final int variacion){
        try {
            if (!guitarProperties.isControl_slide_wheel()){
                
                if (!sliding || !cuerdasSliding.containsKey(disp) || cuerdasSliding.get(disp).intValue()==canal){
                    cuerdasSliding.put(disp, canal);
                  System.out.println(""+new Date()+"haciendo slide up");
                    sliding=true;
                   
 
                    final ShortMessage myMsg = new ShortMessage();
                    legato(true, canal, disp);
                    if (guitarProperties.isControl_slide_onNote()){
                        //114 acou6tic
                        //106 electri6ity
                        myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,guitarProperties.getControl_slide_up(),ultimaFuerza[canal]);
                        devices.get("default").getReceiver().send(myMsg, -1);
                        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);
                    }
                    if (guitarProperties.isControl_slide_toNote()){
                        //114 acou6tic
                        //106 electri6ity
                        myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,nota,ultimaFuerza[canal]);
                        devices.get("default").getReceiver().send(myMsg, -1);
                        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);
                    }
                    
                    
                    if (guitarProperties.isControl_slide_sustainPedal()){
                        myMsg.setMessage(ShortMessage.CONTROL_CHANGE,canal-1,64,115);
                        devices.get("default").getReceiver().send(myMsg, -1);
                        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);
                        hammerOn(disp, canal, nota, variacion);
                    }
                    legato(false, canal, disp);
                    Thread miThread = new Thread(){
                        public void run() {
                            try {
                                Thread.sleep(300);
                            
                            System.out.println(""+new Date()+"haciendo slide up pausa");
                            sliding=false;
                            cuerdasSliding.clear();
                                                if (guitarProperties.isControl_slide_sustainPedal()){
                        myMsg.setMessage(ShortMessage.CONTROL_CHANGE,canal-1,64,0);
                        devices.get("default").getReceiver().send(myMsg, -1);
                        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);
                                                }
                        } catch (InterruptedException ex) {
                                Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
                    }

                        
                    };       
                    miThread.start();
                }

/*
                myMsg.setMessage(ShortMessage.NOTE_ON,canal,nota,ultimaFuerza[canal]);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);
  
                
                
                if (guitarProperties.isControl_slide_onNote()){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    myMsg.setMessage(ShortMessage.NOTE_OFF,canal,guitarProperties.getControl_slide_up(),ultimaFuerza[canal]);
                    devices.get("default").getReceiver().send(myMsg, -1);
                    devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);
                }*/
                //myMsg.setMessage(ShortMessage.NOTE_OFF,canal,slideUp,ultimaFuerza[canal]);
                //device.getReceiver().send(myMsg, -1);
                //devices.get(canal-1).getReceiver().send(myMsg, -1);
            }else{
                slideUp(disp,canal,variacion);
            }
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

        

    public void hammerOn (final int disp,final int canal, final int nota, int variacion){
        try {
                final ShortMessage myMsg = new ShortMessage();
                legato(true, canal, disp);
                myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,guitarProperties.getControl_hammer_on_value(),guitarProperties.getControl_hammer_on_strength());
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);
                
                if (guitarProperties.isControl_hammer_on_keyswitch()){
                    myMsg.setMessage(ShortMessage.NOTE_OFF,canal-1,guitarProperties.getControl_hammer_on_value(),guitarProperties.getControl_hammer_on_strength());
                    devices.get("default").getReceiver().send(myMsg, -1);
                    devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);
                }
Thread miThread = new Thread(){
	public void run() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException ex) {
			Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
		}
                try{
                myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,nota,ultimaFuerza[canal]);
                //myMsg.setMessage(ShortMessage.NOTE_ON,canal,nota,60);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);

                
                //electro6ity 24
                //acou6tic 31
                //scarbee 30
            myMsg.setMessage(ShortMessage.NOTE_ON, canal-1, guitarProperties.getControl_hammer_on_stop(), 100);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);
            myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1, guitarProperties.getControl_hammer_on_stop(), 100);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);

        

                //myMsg.setMessage(ShortMessage.NOTE_OFF,canal,slideDown,ultimaFuerza[canal]);
                //device.getReceiver().send(myMsg, -1);
            System.out.println("hammeron");
            legato(false, canal, disp);
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex1) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (MidiUnavailableException ex1) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex1);
        }
                    
                

	}
};       
miThread.start();                
                
                
                
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        
    public void pullOff (final int disp,final int canal, final int nota, int variacion){
        try {
                final ShortMessage myMsg = new ShortMessage();
                legato(true, canal, disp);
                myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,guitarProperties.getControl_pull_off_value(),guitarProperties.getControl_pull_off_strength());
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);

                if (guitarProperties.isControl_pull_off_keyswitch()){
                    myMsg.setMessage(ShortMessage.NOTE_OFF,canal-1,guitarProperties.getControl_pull_off_value(),guitarProperties.getControl_pull_off_strength());
                    devices.get("default").getReceiver().send(myMsg, -1);
                    devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);
                }
Thread miThread = new Thread(){
	public void run() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException ex) {
			Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
		}
try{
                myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,nota,ultimaFuerza[canal]);
                //myMsg.setMessage(ShortMessage.NOTE_ON,canal,nota,60);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);

                
                //electricyti 24
                //acoustic 31
                
            myMsg.setMessage(ShortMessage.NOTE_ON, canal-1, guitarProperties.getControl_pull_off_stop(), 100);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);
            myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1,guitarProperties.getControl_pull_off_stop(), 100);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);

                
                


                //myMsg.setMessage(ShortMessage.NOTE_OFF,canal,slideDown,ultimaFuerza[canal]);
                //device.getReceiver().send(myMsg, -1);
            System.out.println("pulloff");
            legato(false, canal, disp);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }


	}
};       
miThread.start();            
            
                
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public void legato(boolean iniciar,int canal, int disp){
        final ShortMessage myMsg = new ShortMessage();
        try {
            if (guitarProperties.isControl_legato_onNote()){
                int orden=iniciar?ShortMessage.NOTE_ON:ShortMessage.NOTE_OFF;
                myMsg.setMessage(orden, canal-1, guitarProperties.getControl_legato_value(), 100);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULL_HAMMER)).getReceiver().send(myMsg, -1);
            }
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
        
    public void slide_Down (int disp,int canal, int nota, int variacion){
        try {
            if (!guitarProperties.isControl_slide_wheel()){
                
                
                if (!sliding || !cuerdasSliding.containsKey(disp) || cuerdasSliding.get(disp).intValue()==canal){
                    cuerdasSliding.put(disp, canal);
                    System.out.println(""+new Date()+"haciendo slide down");
                    sliding=true;

                    ShortMessage myMsg = new ShortMessage();
                    legato(true, canal, disp);
                    if (guitarProperties.isControl_slide_onNote()){
                        //104 electricity
                        //111 acou6tic
                        myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,guitarProperties.getControl_slide_down(),ultimaFuerza[canal]);
                        devices.get("default").getReceiver().send(myMsg, -1);
                        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);
                    }
                    
                    if (guitarProperties.isControl_slide_toNote()){
                        //114 acou6tic
                        //106 electri6ity
                        myMsg.setMessage(ShortMessage.NOTE_ON,canal-1,nota,ultimaFuerza[canal]);
                        devices.get("default").getReceiver().send(myMsg, -1);
                        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_UP_ON)).getReceiver().send(myMsg, -1);
                    }
                    legato(false, canal, disp);
                    Thread miThread = new Thread(){
                        public void run() {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println(""+new Date()+"haciendo slide down pausa");
                            sliding=false;
                            cuerdasSliding.clear();
                        }
                    };       
                    miThread.start();                }else{
                    System.out.println("alguien ya estaba con slide");
                }
/*
                myMsg.setMessage(ShortMessage.NOTE_ON,canal,nota,ultimaFuerza[canal]);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);

                if (guitarProperties.isControl_slide_onNote()){
                    myMsg.setMessage(ShortMessage.NOTE_OFF,canal,104,ultimaFuerza[canal]);
                    devices.get("default").getReceiver().send(myMsg, -1);
                    devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.SLIDE_DOWN_ON)).getReceiver().send(myMsg, -1);
                }
*/
            
            }else{
                slideDown(disp,canal,variacion);
            }
            System.out.println("sdown");
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void enviarNota(int disp,int canal,int nota, int fuerza,int cc, int key_string){
        if (guitarProperties.isString_cutAlways()){
            cortarTodosExcepto(disp,canal);
        }
        System.out.println("canal "+canal);
        enviarNota(disp, canal, nota, fuerza, false, cc, key_string);
    }
    
    private void iniciarBending(int disp,int canal,int tonos){
    ShortMessage myMsg = new ShortMessage();
    System.out.println("iniciar bending");
    bendTones=tonos;
    try{
        myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal, 121, 0);
        devices.get("default").getReceiver().send(myMsg, -1);
        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_ON)).getReceiver().send(myMsg, -1);

        myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal, 93, 95);
        devices.get("default").getReceiver().send(myMsg, -1);
        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_ON)).getReceiver().send(myMsg, -1);

        myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal, 91, 127);
        devices.get("default").getReceiver().send(myMsg, -1);
        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_ON)).getReceiver().send(myMsg, -1);
        myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal, 7, 98);
        devices.get("default").getReceiver().send(myMsg, -1);
        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_ON)).getReceiver().send(myMsg, -1);
        myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal, 10, 32);
        devices.get("default").getReceiver().send(myMsg, -1);
        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_ON)).getReceiver().send(myMsg, -1);

        int variacionTotal = tonos*guitarProperties.getControl_bend_wheel_variation();
        for (int i = 1; i <= variacionTotal; i++) {
            myMsg.setMessage(ShortMessage.PITCH_BEND,canal,127,63+(i));
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_ON)).getReceiver().send(myMsg, -1);
            
        }

    } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void datenerBending(int disp,int canal,int tonos){
    ShortMessage myMsg = new ShortMessage();
    System.out.println("deteniendo bending");
    bendTones=0;
    try{
        int variacionTotal = tonos*guitarProperties.getControl_bend_wheel_variation();
        myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal, 121, 0);
        devices.get("default").getReceiver().send(myMsg, -1);
        devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_OFF)).getReceiver().send(myMsg, -1);
        for (int i = variacionTotal; i >= 1; i--) {
            myMsg.setMessage(ShortMessage.PITCH_BEND,canal,127,63+(i));
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_OFF)).getReceiver().send(myMsg, -1);
            
        }
            myMsg.setMessage(ShortMessage.PITCH_BEND,canal,0,64);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_OFF)).getReceiver().send(myMsg, -1);

    } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public void enviarNota(final int disp,final int canal,final int nota, final int fuerza, boolean bending,int cc,  int key_string){
        final ShortMessage myMsg = new ShortMessage();
        try {
            System.out.println("canal "+canal);
            
            myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 121, 0);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);

            if (guitarProperties.getString_go_to_sustain()>-1){
                myMsg.setMessage(ShortMessage.NOTE_ON, canal-1, 24, 100);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);
                myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1, 24, 100);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);
            }
            myMsg.setMessage(ShortMessage.PITCH_BEND, canal-1, 127, 64);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);

            
            myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1, guitarProperties.getControl_pull_off_value(), guitarProperties.getControl_pull_off_strength());
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);
            myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1, guitarProperties.getControl_hammer_on_value(), guitarProperties.getControl_hammer_on_strength());
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);
            
            
/*            myMsg.setMessage(ShortMessage.PITCH_BEND,canal,127,64);
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.BEND_ON)).getReceiver().send(myMsg, -1);
*/
            ultimaCuerda=disp;
            if (slideBool && guitarProperties.isControl_slide_onNote() && !guitarProperties.isControl_slide_wheel()){
          //      stop_Slide(disp,canal);
          //      start_Slide(disp,canal);
            }
            //myMsg.setMessage(ShortMessage.NOTE_OFF, canal, bend, fuerza); 
            //devices.get("default").getReceiver().send(myMsg, -1);
            //devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.CORTE)).getReceiver().send(myMsg, -1);
            //cambiarCuerda(disp, canal, 0);
            cambiarCuerda(disp, canal, cc,key_string);
            /*
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            
       /*     Thread miThread = new Thread(){
	public void run() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException ex) {
			Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
		}*/
try{
            myMsg.setMessage(ShortMessage.NOTE_ON, canal-1, nota, fuerza); 
            if (ultimaNota[canal]>=0 && ultimaNota[canal]!=nota){
 
               cortarNota( disp,canal, ultimaNota[canal], ultimaFuerza[canal]); 
            }
                    
            ultimaNota[canal]=nota;
            ultimaFuerza[canal]=fuerza;
            ultimaPulsada[canal]=new Date();
            System.out.println("canal "+canal);
            myMsg.setMessage(ShortMessage.NOTE_ON, canal-1, nota, fuerza); 
            devices.get("default").getReceiver().send(myMsg, -1);
            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);

                    } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }


	/*}
};       
miThread.start();*/
            
            
            /*
            if (bendTones>0){
                try {
                    Thread.sleep(75);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                }
                iniciarBending(disp, canal, bendTones);
            }*/
            //cambiarCuerda(disp, canal, 0);
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    public void cortarNotaDelay(int disp,int canal,int nota, int fuerza, Date pulsada){
        final int disp1 = disp;
        final int canal1 = canal;
        final int nota1 = nota;
        final int fuerza1 = fuerza;
        final Date pulsada1 = pulsada;
        Thread miThread = new Thread(){
            public void run() {
                try {
                    this.sleep(fuerza1*10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ultimaNota[canal1]==nota1 && ultimaPulsada[canal1].equals(pulsada1)){ 
                    cortarNota(disp1, canal1, nota1, fuerza1);
                }
                
            }
        };
        miThread.start();
                
    }

    public void cambiarCuerda(final int disp, final int canal, final int cc, final int keyString){
        final ShortMessage myMsg = new ShortMessage();
        try {
            if(guitarProperties.isString_cc_selection()){
                myMsg.setMessage(ShortMessage.CONTROL_CHANGE, canal-1, 32, cc);
                System.out.println("envia a cuerda "+cc);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);
            }
        if (guitarProperties.isString_key_selection()){
            Thread miThread = new Thread(){
                public void run() {
                        //        myMsg.setMessage(ShortMessage.NOTE_ON, canal, cc, 100);
            System.out.println("envia a cuerda "+cc);
                

                        try {
                            if (ultimaCuerdaSeleccionada>0){
                                myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1, ultimaCuerdaSeleccionada, 100);
                                devices.get("default").getReceiver().send(myMsg, -1);
                                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);
                                
                            }
                            ultimaCuerdaSeleccionada=keyString;
                            myMsg.setMessage(ShortMessage.NOTE_ON, canal-1, keyString, 100);
                            devices.get("default").getReceiver().send(myMsg, -1);
                            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);

                            Thread.sleep(100);
                        /*
                            myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1, keyString, 100);
                            System.out.println("envia a cuerda "+cc);
                            devices.get("default").getReceiver().send(myMsg, -1);
                            devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.PULSADA)).getReceiver().send(myMsg, -1);
                            */
                        } catch (InterruptedException ex) {
                                Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                        }catch (InvalidMidiDataException ex) {
                        Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MidiUnavailableException ex) {
                        Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
            };       
            miThread.start();
        }
            
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cortarNota(int disp,int canal,int nota, int fuerza){
        ShortMessage myMsg = new ShortMessage();
        try {
            if (canal>=0 && nota >=0){
            //stop_Slide(canal);
                myMsg.setMessage(ShortMessage.NOTE_OFF, canal-1, nota, fuerza);
                devices.get("default").getReceiver().send(myMsg, -1);
                devices.get(controlSalida.obtenerPuerto(disp,EnumeradoOrdenes.CORTE)).getReceiver().send(myMsg, -1);
        }
            //devices.get(canal-1).getReceiver().send(myMsg, -1);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SalidaMidi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void changeString(){
    }
    
    public void cortarTodos(){
        if (guitarProperties.isControl_mute_note()){
            
        }
        for (int i=1;i<=6;i++){
            if ((ultimaNota[i]>-10) && (ultimaFuerza[i]>-10)){
                cortarNota(i,i, ultimaNota[i],ultimaFuerza[i]);
                cortarNota(i,i, slideDown,ultimaFuerza[i]);
                cortarNota(i,i, slideUp,ultimaFuerza[i]);
                
                ultimaNota[i]=-10;
                ultimaFuerza[i]=-10;
            }
        }
        /*
        if (guitarProperties.isControl_mute_note()){
            enviarNota(1, guitarProperties.getControl_mute_note_value(), 60);
            cortarNota(1, guitarProperties.getControl_mute_note_value(), 600);
        }
*/
    }
    
        public void cortarTodosExcepto(int disp,int canal){
        for (int i=1;i<=6;i++){
            if (canal!=i){
                if ((ultimaNota[i]>-10) && (ultimaFuerza[i]>-10)){
                    cortarNota(i,i, ultimaNota[i],ultimaFuerza[i]);
                    cortarNota(i,i, slideDown,ultimaFuerza[i]);
                    cortarNota(i,i, slideUp,ultimaFuerza[i]);

                    ultimaNota[i]=-10;
                    ultimaFuerza[i]=-10;
                }
            }
        }
    }
    
    
    public void update(Observable o, Object arg) {
        
        if (arg instanceof NotaMidi){
           NotaMidi nota = (NotaMidi) arg;
           enviarNota(nota.getDisp(), nota.getCanal(), nota.getNota(), nota.getFuerza(),0,0);
    
        }else if (arg instanceof OrdenApagado){
            cortarTodos();
        }else if (arg instanceof OrdenPulsada){
            OrdenPulsada orden = (OrdenPulsada)arg;
            enviarNota(orden.getNota().getDisp(), orden.getNota().getCanal(),orden.getNota().getNota(),orden.getNota().getFuerza(),orden.getCc(),orden.getKey_string());
            
        }else if (arg instanceof OrdenPulsadaBend){
            OrdenPulsadaBend orden = (OrdenPulsadaBend)arg;
            enviarNota(orden.getNota().getDisp(),orden.getNota().getCanal(),orden.getNota().getNota(),orden.getNota().getFuerza(), true,orden.getCc(), orden.getKey_string());
            
        }else if (arg instanceof OrdenPullOff){
            OrdenPullOff orden = (OrdenPullOff)arg;
            pullOff(orden.getDisp(),orden.getCanal(),orden.getNota(),orden.getVariacion());
        }else if (arg instanceof OrdenHammerOn){            
            OrdenHammerOn orden = (OrdenHammerOn)arg;
            hammerOn(orden.getDisp(),orden.getCanal(),orden.getNota(),orden.getVariacion());

        }else if (arg instanceof OrdenSlideDown){
            OrdenSlideDown orden = (OrdenSlideDown)arg;
            if (guitarProperties.isControl_slide_wheel()){
                slideDown(orden.getDisp(),orden.getCanal(), orden.getVariacion());
            }else{
                slide_Down(orden.getDisp(),orden.getCanal(),orden.getNota(),orden.getVariacion());
            }
        }else if (arg instanceof OrdenSlideUp){
            OrdenSlideUp orden = (OrdenSlideUp)arg;
            if (guitarProperties.isControl_slide_wheel()){
                slideUp(orden.getDisp(),orden.getCanal(), orden.getVariacion());
            }else{
                slide_Up(orden.getDisp(),orden.getCanal(),orden.getNota(),orden.getVariacion());
            }
        }else if (arg instanceof OrdenSlide){
            OrdenSlide orden = (OrdenSlide)arg;
            slideP(orden.getDisp(),orden.getCanal(), orden.getNota());
        }else if (arg instanceof OrdenApagadoCuerda){
            OrdenApagadoCuerda orden = (OrdenApagadoCuerda)arg;
            cortarNota(orden.getDispositivo(),orden.getCanal(),ultimaNota[orden.getCanal()],ultimaFuerza[orden.getCanal()]);
        }else if (arg instanceof OrdenApagadoCuerdaDelay){
            OrdenApagadoCuerdaDelay orden = (OrdenApagadoCuerdaDelay)arg;
            cortarNotaDelay(orden.getDispositivo(),orden.getCanal(),ultimaNota[orden.getCanal()],ultimaFuerza[orden.getCanal()],ultimaPulsada[orden.getCanal()]);

        }else if (arg instanceof OrdenSwitchOnOffSlide){
            slideBool=!slideBool;
            if (!slideBool){
                stop_Slide(1,1);
            }
        }else if (arg instanceof OrdenBendUp){
            OrdenBendUp orden = (OrdenBendUp)arg;
            if (ultimaCuerda==orden.getDisp()){
                iniciarBending(orden.getDisp(), 0,orden.getTones());
            }
        }else if (arg instanceof OrdenBendOff){
            OrdenBendOff orden = (OrdenBendOff)arg;
            if (ultimaCuerda==orden.getDisp()){
                datenerBending(orden.getDisp(), 0,orden.getTones());
            }
        }else if (arg instanceof OrdenStopSlide){
            OrdenStopSlide orden = (OrdenStopSlide)arg;
            stop_Slide(orden.getDisp(), orden.getCanal());
        }else if (arg instanceof OrdenCambioCuerda){
          //  OrdenCambioCuerda orden = (OrdenCambioCuerda)arg;
          //  cambiarCuerda(orden.getDispositivo(), orden.getCanal(), orden.getValor());
        }
            
        
    }

    public GuitarProperties getGuitarProperties() {
        return guitarProperties;
    }

    public void setGuitarProperties(GuitarProperties guitarProperties) {
        this.guitarProperties = guitarProperties;
        this.slideUp = guitarProperties.getControl_slide_up();
        this.slideDown = guitarProperties.getControl_slide_down();
        this.bend = guitarProperties.getControl_bend_up();
    }
    
    
    
}
