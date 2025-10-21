/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra;

import coira.guitarra.eventos.EventoLegato;
import coira.guitarra.eventos.EventoTrasteCuerda;
import coira.guitarra.ordenes.OrdenApagadoCuerda;
import coira.guitarra.ordenes.OrdenHammerOn;
import coira.guitarra.ordenes.OrdenPullOff;
import coira.guitarra.ordenes.OrdenPulsada;
import coira.guitarra.ordenes.OrdenSlideDown;
import coira.guitarra.ordenes.OrdenSlideUp;
import coira.properties.GuitarProperties;
import coira.salida.GuitarraMustangSalida;
import configuraciones.cuerdas.CFGCuerdas;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Cuerda {
    
    private static final long MIN_SUSTAIN = 200;   // ms
    private static final long MAX_SUSTAIN = 2000;  // ms
    private String modoSustain = "E";
    private int tramos = 5;
    private int primerNota = -10;
    private int canal = -10;
    private String dispositivo = null;
    private int offset =0;
    private int bendingTones;
    private boolean pulsada = false;
    private boolean slide = false;
    private boolean bend = false;
    private Date fechaSuelto = new Date(0);
    private HashMap <Integer,Integer> controlBending = new HashMap<Integer, Integer>();
    
    //GuitarProperties guitarProperties;
    
    private int ultimoTraste = 0;
    private int ultimoTrastePresionado = 0;

    private int ultimoTrastePulsado = 0;
    private int ultimaPulsada = -10000;
    private int ultimaFuerza = 0;
    private int cuerdaPosicion=0;
    private Date momentoPulsada = new Date(0);
    private int cc;
    private int key_string;
    private int tiempoToSlide = 1000;
    private boolean correspondeLegato = false;
    private String puerto;
    private String nombre = "";
    public Cuerda() {
    }

    public Cuerda(int primerNota, int canal, String dispositivo,int cc, int key_string) {
        this.primerNota=primerNota;
        this.canal=canal;
        this.dispositivo=dispositivo;
        this.cc=cc;
        this.key_string=key_string;
    }


    
    public Cuerda(String nombre,int primerNota, int canal, String puerto, int cuerdaPosicion){
        this.primerNota=primerNota;
        this.canal=canal;
        this.puerto=puerto;
        this.cuerdaPosicion = cuerdaPosicion;
        this.nombre = nombre;
    }
    
    public Cuerda(int primerNota, int canal, String puerto, int cuerdaPosicion, String modo, int tramos){
        this.primerNota=primerNota;
        this.canal=canal;
        this.puerto=puerto;
        this.cuerdaPosicion = cuerdaPosicion;
        this.tramos=tramos;
        this.modoSustain=modo;
        
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

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getCuerdaPosicion() {
        return cuerdaPosicion;
    }

    public void setCuerdaPosicion(int cuerdaPosicion) {
        this.cuerdaPosicion = cuerdaPosicion;
    }

    public String getModoSustain() {
        return modoSustain;
    }

    public void setModoSustain(String modoSustain) {
        this.modoSustain = modoSustain;
    }

    public int getTramos() {
        return tramos;
    }

    public void setTramos(int tramos) {
        this.tramos = tramos;
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

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
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
    
    
    public int modificoFuerza(int variacion){
        int smax = CFGCuerdas.getInstance().getMax_Strength();
        int smin = CFGCuerdas.getInstance().getMin_Strength();
        int aux =variacion<0?variacion+128:variacion;
        if (aux>0){
            if (aux  > smax){
                return smax;
            } else if (smin > aux) {
                return smax;
            } else {
                return aux;
            }
        }else{
           return aux;
            
        }
    }


    public void setGuitarProperties(GuitarProperties guitarProperties) {

        reseteoTonoInicial();
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
            
            GuitarraMustangSalida.getInstance().ejecutar(new EventoTrasteCuerda(traste, this.getCanal(), this.getCuerdaPosicion(),nombre));
        }

        
        if (correspondeLegato){
           
          //  System.out.println("Corresponde Legato deteccion variacion "+correspondeLegato);
        }
        
        if (cuerda!=ultimaPulsada && Math.abs(cuerda-ultimaPulsada)!=128){
             
            if (ultimaPulsada>-10000 ){
                correspondeLegato=false;
                System.out.println("Corresponde Legato pulsada "+correspondeLegato);
                int fuerza = modificoFuerza(cuerda);
                System.out.println("fuerza " + fuerza);
                
                
                GuitarraMustangSalida.getInstance().ejecutar(new OrdenPulsada(traste+primerNota+offset, fuerza,nombre,this));
                
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
                
                GuitarraMustangSalida.getInstance().ejecutar(new OrdenPullOff(variacion.getVariacion(),ultimaFuerza ,nombre, Cuerda.this));
            }else if (variacion.getVariacion() > 0 && !variacion.isSlide()){
                
                GuitarraMustangSalida.getInstance().ejecutar(new OrdenHammerOn(variacion.getVariacion(),ultimaFuerza ,nombre, Cuerda.this));
            }else if (variacion.getVariacion() < 0  && variacion.isSlide()){
                
                GuitarraMustangSalida.getInstance().ejecutar(new OrdenSlideDown(variacion.getVariacion(),ultimaFuerza ,nombre, Cuerda.this));
            }else if (variacion.getVariacion() > 0 && variacion.isSlide()){
                
                GuitarraMustangSalida.getInstance().ejecutar(new OrdenSlideUp(variacion.getVariacion(),ultimaFuerza ,nombre, Cuerda.this));

            }
        }

    }
};       
miThread.start();
            
            
        }

        ultimoTrastePresionado = traste;
        
    }
    
    
    
    private EventoLegato detectarSlide(int traste){
        Date ahora = new Date();
        
        EventoLegato retorno = new EventoLegato(0, false);
    //    if (slide){
            if (ultimoTrastePresionado != traste &&   ahora.getTime()-momentoPulsada.getTime()<=obtenerSustain(ultimaPulsada)){
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
    //    }
        if (traste == 0 && traste != ultimoTrastePresionado && momentoPulsada.getTime()>=tiempoToSlide){
            fechaSuelto = new Date();
          /*  if (guitarProperties.isControl_string_muteOnRelease()){
                
                GuitarraMustangSalida.getInstance().ejecutar(new OrdenApagadoCuerdaDelay(dispositivo,canal));
            }*/
        }else if (traste != 0 && ultimoTrastePresionado == 0){
                
                GuitarraMustangSalida.getInstance().ejecutar(new OrdenApagadoCuerda(dispositivo,canal));

            
        }
        return retorno;
    }

private long sustainLineal(int velocity) {
    return MIN_SUSTAIN + (long)((velocity / 127.0) * (MAX_SUSTAIN - MIN_SUSTAIN));
}

private long sustainExponencial(int velocity) {
    double factor = (double) velocity / 127.0;
    return (long)(MIN_SUSTAIN * Math.pow((double) MAX_SUSTAIN / MIN_SUSTAIN, factor));
}
 

private long obtenerSustain(int velocity){
    if (modoSustain.equals("L")){
        return sustainLineal(velocity);
    } else if (modoSustain.equals("T")){
        return sustainPorTramos(velocity, tramos);
    } else if (modoSustain.equals("E")){
        return sustainExponencial(velocity);
    } else {
        return sustainExponencial(velocity);
    }
}


private long sustainPorTramos(int velocity, int tramos) {
    if (tramos < 1) tramos = 1; // mínimo un tramo

    // tamaño del tramo en velocity
    double velStep = 127.0 / tramos;
    // tamaño del tramo en sustain
    double susStep = (MAX_SUSTAIN - MIN_SUSTAIN) / (double) tramos;

    // en qué tramo cae este velocity
    int tramoIndex = (int)(velocity / velStep);

    // valor dentro del tramo
    double tramoProgress = (velocity % velStep) / velStep;

    // sustain base del tramo + progreso dentro del tramo
    return (long)(MIN_SUSTAIN + tramoIndex * susStep + tramoProgress * susStep);
}


    
}
