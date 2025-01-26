/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra;

import coira.Midi.NotaMidi;
import coira.guitarra.eventos.EventoLegato;
import coira.guitarra.eventos.EventoTrasteCuerda;
import coira.guitarra.ordenes.OrdenApagadoCuerda;
import coira.guitarra.ordenes.OrdenApagadoCuerdaDelay;
import coira.guitarra.ordenes.OrdenBajoFuerza;
import coira.guitarra.ordenes.OrdenBendOff;
import coira.guitarra.ordenes.OrdenBendUp;
import coira.guitarra.ordenes.OrdenBoton2;
import coira.guitarra.ordenes.OrdenBotonMas;
import coira.guitarra.ordenes.OrdenBotonMasMenos;
import coira.guitarra.ordenes.OrdenBotonMenos;
import coira.guitarra.ordenes.OrdenCambioCuerda;
import coira.guitarra.ordenes.OrdenHammerOn;
import coira.guitarra.ordenes.OrdenMovePadOff;
import coira.guitarra.ordenes.OrdenPullOff;
import coira.guitarra.ordenes.OrdenPulsada;
import coira.guitarra.ordenes.OrdenPulsadaBend;
import coira.guitarra.ordenes.OrdenReseteoFuerza;
import coira.guitarra.ordenes.OrdenSlide;
import coira.guitarra.ordenes.OrdenSlideDown;
import coira.guitarra.ordenes.OrdenSlideUp;
import coira.guitarra.ordenes.OrdenStopSlide;
import coira.guitarra.ordenes.OrdenSuboFuerza;
import coira.guitarra.ordenes.OrdenSwitchOnOffSlide;
import coira.guitarra.ordenes.OrdenToNeck;
import coira.guitarra.ordenes.OrdenToStrings;
import coira.properties.GuitarProperties;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Cuerda extends Observable implements Observer{
    private int primerNota = -10;
    private int canal = -10;
    private int dispositivo = -10;
    private int offset =0;
    private int bendingTones;
    private boolean pulsada = false;
    private boolean slide = false;
    private boolean bend = false;
    private Date fechaSuelto = new Date(0);
    private HashMap <Integer,Integer> controlBending = new HashMap<Integer, Integer>();
    
    GuitarProperties guitarProperties;
    
    private int ultimoTraste = 0;
    private int ultimoTrastePresionado = 0;
    private int offSetFuerzaMax =0;
    private int offSetFuerzaMin =0;
    private int ultimoTrastePulsado = 0;
    private int ultimaPulsada = -10000;
    private Date momentoPulsada = new Date(0);
    private int cc;
    private int key_string;
    private int tiempoToSlide = 1000;
    private boolean correspondeLegato = false;
    public Cuerda() {
    }

    public Cuerda(int primerNota, int canal, int dispositivo,int cc, int key_string) {
        this.primerNota=primerNota;
        this.canal=canal;
        this.dispositivo=dispositivo;
        this.cc=cc;
        this.key_string=key_string;
    }


    
    
    
    public int getPrimerNota() {
        return primerNota;
    }

    public void setPrimerNota(int primerNota) {
        this.primerNota = primerNota;
    }

    public boolean isPulsada() {
        return pulsada;
    }

    public int getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(int dispositivo) {
        this.dispositivo = dispositivo;
    }

    
    
    
    public void setPulsada(boolean pulsada) {
        this.pulsada = pulsada;
    }

    public boolean isSlide() {
        return slide;
    }

    public void setSlide(boolean slide) {
        this.slide = slide;
    }

    public int getUltimoTrastePulsado() {
        return ultimoTrastePulsado;
    }

    public void setUltimoTrastePulsado(int ultimoTrastePulsado) {
        this.ultimoTrastePulsado = ultimoTrastePulsado;
    }

    public Date getMomentoPulsada() {
        return momentoPulsada;
    }

    public void setMomentoPulsada(Date momentoPulsada) {
        this.momentoPulsada = momentoPulsada;
    }


    
    
    public int getUltimoTraste() {
        return ultimoTraste;
    }

    public void setUltimoTraste(int ultimoTraste) {
        this.ultimoTraste = ultimoTraste;
    }

    public int getUltimaPulsada() {
        return ultimaPulsada;
    }

    public void setUltimaPulsada(int ultimaPulsada) {
        this.ultimaPulsada = ultimaPulsada;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }
    
    public void modificoTonoInicial(int modificacion){
        offset=offset+modificacion;
    }
    
    
    public void modificoFuerza(int variacion){
        int smax = guitarProperties.getString_strength_max();
        int smin = guitarProperties.getString_strength_min();

        if (variacion>0){
            offSetFuerzaMax=(smax+offSetFuerzaMax)<127?offSetFuerzaMax+variacion:offSetFuerzaMax;
            offSetFuerzaMin=(smin+offSetFuerzaMin)<127?offSetFuerzaMin+variacion:offSetFuerzaMin;
        }else{
            offSetFuerzaMax=(smax+offSetFuerzaMax)>7?offSetFuerzaMax+variacion:offSetFuerzaMax;
            offSetFuerzaMin=(smin+offSetFuerzaMin)>7?offSetFuerzaMin+variacion:offSetFuerzaMin;
            
        }
    }

    public GuitarProperties getGuitarProperties() {
        return guitarProperties;
    }

    public void setGuitarProperties(GuitarProperties guitarProperties) {
        this.guitarProperties = guitarProperties;
        reseteoFuerza();
        reseteoTonoInicial();
    }
    
    
    public void reseteoFuerza (){
        offSetFuerzaMax=0;
        offSetFuerzaMin=0;
        
    }
    public void reseteoTonoInicial(){
        offset=0;
    }
    
    public void interpretoEvento(final int traste, int cuerda){
        Date ahora = new Date();
        final EventoLegato variacion = detectarSlide(traste);
        if (variacion.getVariacion()!=0){
            System.out.println("var "+variacion);
        }
        if (ultimoTrastePresionado!=traste){
            setChanged();
            notifyObservers(new EventoTrasteCuerda(traste, this.getCanal()));
        }

        
        if (correspondeLegato){
           
          //  System.out.println("Corresponde Legato deteccion variacion "+correspondeLegato);
        }
        
        if (cuerda!=ultimaPulsada && Math.abs(cuerda-ultimaPulsada)!=128){
             
            if (ultimaPulsada>-10000 ){
                correspondeLegato=false;
                System.out.println("Corresponde Legato pulsada "+correspondeLegato);
                int aux =cuerda<0?cuerda+128:cuerda;
                System.out.println("fuerza " +aux);
                setChanged();
                setChanged();
                notifyObservers(new OrdenPulsada(new NotaMidi(dispositivo,traste+primerNota+offset, canal, cuerda,guitarProperties.getString_strength_min()+offSetFuerzaMin,guitarProperties.getString_strength_max()+offSetFuerzaMax),cc,key_string));
                
                if (traste>0 && aux>guitarProperties.getString_bend_strength()){
                    bend=true;
                }else{
                    bend=false;
                    bendingTones=0;
                }
                
            }
            ultimaPulsada = cuerda;

            ultimoTraste = traste;
            ultimoTrastePresionado = traste;
            momentoPulsada = new Date();
            ultimoTrastePulsado =traste;
        }else if (variacion.getVariacion()!=0){
            
            
Thread miThread = new Thread(){
    public void run() {
        correspondeLegato=variacion.getVariacion()!=0;
        System.out.println("Corresponde Legato esperando "+correspondeLegato);
        try {
                Thread.sleep(30);
        } catch (InterruptedException ex) {
                Logger.getLogger(Cuerda.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Corresponde Legato luego de esperar "+correspondeLegato);
        if (correspondeLegato){
            if (variacion.getVariacion() < 0 && !variacion.isSlide()){
                setChanged();
                notifyObservers(new OrdenPullOff(dispositivo,variacion.getVariacion(),canal,false,traste+primerNota+offset));
            }else if (variacion.getVariacion() > 0 && !variacion.isSlide()){
                setChanged();
                notifyObservers(new OrdenHammerOn(dispositivo,variacion.getVariacion(),canal,false,traste+primerNota+offset));
            }else if (variacion.getVariacion() < 0  && variacion.isSlide()){
                setChanged();
                notifyObservers(new OrdenSlideDown(dispositivo,variacion.getVariacion(),canal,false,traste+primerNota+offset));
            }else if (variacion.getVariacion() > 0 && variacion.isSlide()){
                setChanged();
                notifyObservers(new OrdenSlideUp(dispositivo,variacion.getVariacion(),canal,false,traste+primerNota+offset));

            }else if (variacion.getVariacion() == -10){
                    setChanged();
                notifyObservers(new OrdenStopSlide(dispositivo,0,canal,false,traste+primerNota+offset));

            }
        }

    }
};       
miThread.start();
            
            
        }
            //ultimoTraste = traste;
        ultimoTrastePresionado = traste;
        
    }
    
    
    
    private EventoLegato detectarSlide(int traste){
        Date ahora = new Date();
        
        EventoLegato retorno = new EventoLegato(0, false);
        if (slide){
            if (ultimoTrastePresionado != traste &&   ahora.getTime()-momentoPulsada.getTime()<tiempoToSlide){
            //if (ultimoTrastePresionado != traste && traste!=0 && ultimoTraste !=0 && ultimoTrastePresionado != 0){// && ahora.getTime()-momentoPulsada.getTime()<tiempoToSlide){
            momentoPulsada= new Date();
                retorno.setVariacion(traste - ultimoTrastePresionado);
                if ((Math.abs(traste-ultimoTrastePresionado)>1 &&(traste==0 ||ultimoTrastePresionado ==0 ))){
                    //traste==ultimoTraste || 
                    retorno.setVariacion(0);
                    //retorno = -10;
                }else if (Math.abs(traste-ultimoTrastePresionado)>1 || (Math.abs(traste-ultimoTrastePresionado)==1 &&(traste==0 ||ultimoTrastePresionado ==0 ))){
                    retorno.setSlide(false);
                }else{
                    retorno.setSlide(true);
                }
                
            }
            if (retorno.getVariacion()!=0){
            System.out.println("Varacion : "+retorno.getVariacion()+" slide "+retorno.isSlide());
            }
        }
        if (traste == 0 && traste != ultimoTrastePresionado && momentoPulsada.getTime()>=tiempoToSlide){
            fechaSuelto = new Date();
            if (guitarProperties.isControl_string_muteOnRelease()){
                setChanged();
                notifyObservers(new OrdenApagadoCuerdaDelay(dispositivo,canal));
            }
        }else if (traste != 0 && ultimoTrastePresionado == 0){

//            Date nuevaPresion = new Date();
//            if (nuevaPresion.getTime()-fechaSuelto.getTime()<100){
                setChanged();
                notifyObservers(new OrdenApagadoCuerda(dispositivo,canal));
//            }
            
        }
        return retorno;
    }


    public void update(Observable o, Object arg) {
        //System.out.println(arg.getClass().getName());
        if (arg instanceof OrdenBotonMas){
            this.modificoTonoInicial(1);
        }else if(arg instanceof OrdenBotonMenos){
            this.modificoTonoInicial(-1);
        }else if(arg instanceof OrdenBotonMasMenos){
            this.reseteoTonoInicial();
        }else if (arg instanceof OrdenSuboFuerza){
            this.modificoFuerza(guitarProperties.getString_strength_var());
        }else if(arg instanceof OrdenBajoFuerza){
            this.modificoFuerza(guitarProperties.getString_strength_var()*-1);
        }else if(arg instanceof OrdenReseteoFuerza){
            this.reseteoFuerza();
        }else if (arg instanceof OrdenBoton2){
            slide = !slide;
        }else if (arg instanceof OrdenMovePadOff){
            if (guitarProperties.isControl_bend_wheel()){
                setChanged();
                notifyObservers(new OrdenBendOff(dispositivo,bendingTones));
                bendingTones = 0;
            }else{
                bend = false;
            }
        }else if (arg instanceof OrdenToStrings){
            if (guitarProperties.isControl_bend_wheel()){
                setChanged();
                bendingTones = guitarProperties.getControl_bend_wheel_tones();
                notifyObservers(new OrdenBendUp(dispositivo,bendingTones));

            }else{
                bend = true;
            }
        }else if (arg instanceof OrdenToNeck){
            if (guitarProperties.isControl_bend_wheel()){
                setChanged();
                bendingTones = guitarProperties.getControl_tremolo_wheel_tones();
                notifyObservers(new OrdenBendUp(dispositivo,bendingTones));

            }else{
                bend = true;
            }
        }else if (arg instanceof EventoTrasteCuerda){
            if(bend){

                   EventoTrasteCuerda e = (EventoTrasteCuerda)arg;
                   if (e.getCanal()!=this.getCanal()){
                       controlBending.put(e.getCanal(), e.getTraste());
                       int bendingTonesAux=0;
                       for (int traste:controlBending.values()){
                           if (traste==ultimoTrastePresionado){
                            bendingTonesAux=bendingTonesAux==1?2: bendingTonesAux+2;
                            bendingTonesAux=bendingTonesAux>4?4:bendingTonesAux;
                           }else if (Math.abs(traste-ultimoTrastePresionado)==1){
                               bendingTonesAux=1;
                           }
                       }
                       if (bendingTonesAux>bendingTones){
                            setChanged();
                            notifyObservers(new OrdenBendUp(dispositivo,bendingTonesAux));
                       }else{
                            setChanged();
                            notifyObservers(new OrdenBendOff(dispositivo,bendingTones-bendingTonesAux));
                       }
                       bendingTones=bendingTonesAux;

                    }else{
                        bend=false;
                        setChanged();
                        notifyObservers(new OrdenBendOff(dispositivo,bendingTones));
                        controlBending.clear();
                        bendingTones=0;
                        //si el cambio es en la misma cuerda deja de hacer bending
                    }
                }
            }
        
    
    }
}
