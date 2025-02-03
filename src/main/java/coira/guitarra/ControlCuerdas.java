/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra;

import coira.properties.GeneralProperties;
import coira.properties.GuitarProperties;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Usuario
 */
public class ControlCuerdas extends Observable {
    
    Cuerda cuerda_E ;
    Cuerda cuerda_A ;
    Cuerda cuerda_D ;
    Cuerda cuerda_G ;
    Cuerda cuerda_B ;
    Cuerda cuerda_e ;
    
    GuitarProperties guitarProperties;
    GeneralProperties prop;

    public Cuerda getCuerda_E() {
        return cuerda_E;
    }

    public void setCuerda_E(Cuerda cuerda_E) {
        this.cuerda_E = cuerda_E;
    }

    public Cuerda getCuerda_A() {
        return cuerda_A;
    }

    public void setCuerda_A(Cuerda cuerda_A) {
        this.cuerda_A = cuerda_A;
    }

    public Cuerda getCuerda_D() {
        return cuerda_D;
    }

    public void setCuerda_D(Cuerda cuerda_D) {
        this.cuerda_D = cuerda_D;
    }

    public Cuerda getCuerda_G() {
        return cuerda_G;
    }

    public void setCuerda_G(Cuerda cuerda_G) {
        this.cuerda_G = cuerda_G;
    }

    public Cuerda getCuerda_B() {
        return cuerda_B;
    }

    public void setCuerda_B(Cuerda cuerda_B) {
        this.cuerda_B = cuerda_B;
    }

    public Cuerda getCuerda_e() {
        return cuerda_e;
    }

    public void setCuerda_e(Cuerda cuerda_e) {
        this.cuerda_e = cuerda_e;
    }

    public GuitarProperties getGuitarProperties() {
        return guitarProperties;
    }

    public void setGuitarProperties(GuitarProperties guitarProperties) {
        this.guitarProperties = guitarProperties;
        cuerda_E.setGuitarProperties(guitarProperties);
        cuerda_E.setPrimerNota(guitarProperties.getString_initial_value_lowE());
        cuerda_A.setGuitarProperties(guitarProperties);
        cuerda_A.setPrimerNota(guitarProperties.getString_initial_value_A());
        cuerda_D.setGuitarProperties(guitarProperties);
        cuerda_D.setPrimerNota(guitarProperties.getString_initial_value_D());
        cuerda_G.setGuitarProperties(guitarProperties);
        cuerda_G.setPrimerNota(guitarProperties.getString_initial_value_G());
        cuerda_B.setGuitarProperties(guitarProperties);
        cuerda_B.setPrimerNota(guitarProperties.getString_initial_value_B());
        cuerda_e.setGuitarProperties(guitarProperties);
        cuerda_e.setPrimerNota(guitarProperties.getString_initial_value_highE());
    }

    public ControlCuerdas(GuitarProperties gp, GeneralProperties prop) {
    
        guitarProperties = gp;
        this.prop = prop;
        cuerda_E = new Cuerda(guitarProperties.getString_initial_value_lowE(), guitarProperties.getString_initial_channel_lowE(),prop.getPropertyAsInt("midi.port.String.lowE", guitarProperties.getString_initial_channel_lowE()),guitarProperties.getString_cc_selection_value_lowE(),guitarProperties.getString_key_selection_value_lowE());
        cuerda_A = new Cuerda(guitarProperties.getString_initial_value_A(), guitarProperties.getString_initial_channel_A(),prop.getPropertyAsInt("midi.port.String.A", guitarProperties.getString_initial_channel_A()),guitarProperties.getString_cc_selection_value_A(),guitarProperties.getString_key_selection_value_A());
        cuerda_D = new Cuerda(guitarProperties.getString_initial_value_D(), guitarProperties.getString_initial_channel_D(),prop.getPropertyAsInt("midi.port.String.D", guitarProperties.getString_initial_channel_D()),guitarProperties.getString_cc_selection_value_D(),guitarProperties.getString_key_selection_value_D());
        cuerda_G = new Cuerda(guitarProperties.getString_initial_value_G(), guitarProperties.getString_initial_channel_G(),prop.getPropertyAsInt("midi.port.String.G", guitarProperties.getString_initial_channel_G()),guitarProperties.getString_cc_selection_value_G(),guitarProperties.getString_key_selection_value_G());
        cuerda_B = new Cuerda(guitarProperties.getString_initial_value_B(), guitarProperties.getString_initial_channel_B(),prop.getPropertyAsInt("midi.port.String.B", guitarProperties.getString_initial_channel_B()),guitarProperties.getString_cc_selection_value_B(),guitarProperties.getString_key_selection_value_B());
        cuerda_e = new Cuerda(guitarProperties.getString_initial_value_highE(), guitarProperties.getString_initial_channel_highE(),prop.getPropertyAsInt("midi.port.String.highE", guitarProperties.getString_initial_channel_highE()),guitarProperties.getString_cc_selection_value_highE(),guitarProperties.getString_key_selection_value_highE());
        cuerda_E.setGuitarProperties(guitarProperties);
        cuerda_A.setGuitarProperties(guitarProperties);
        cuerda_D.setGuitarProperties(guitarProperties);
        cuerda_G.setGuitarProperties(guitarProperties);
        cuerda_B.setGuitarProperties(guitarProperties);
        cuerda_e.setGuitarProperties(guitarProperties);
        addObserver(cuerda_E);
        addObserver(cuerda_A);
        addObserver(cuerda_D);
        addObserver(cuerda_G);
        addObserver(cuerda_B);
        addObserver(cuerda_e);
    
    }
    
    public void detectoCambio(Map<Integer,DataCuerda> cuerdas ){
        
        cuerda_E.interpretoEvento(cuerdas.get(6).traste,cuerdas.get(6).fuerzaGolpe);
        cuerda_A.interpretoEvento(cuerdas.get(5).traste,cuerdas.get(6).fuerzaGolpe);
        cuerda_D.interpretoEvento(cuerdas.get(4).traste,cuerdas.get(6).fuerzaGolpe);
        cuerda_G.interpretoEvento(cuerdas.get(3).traste,cuerdas.get(6).fuerzaGolpe);
        cuerda_B.interpretoEvento(cuerdas.get(2).traste,cuerdas.get(6).fuerzaGolpe);
        cuerda_e.interpretoEvento(cuerdas.get(1).traste,cuerdas.get(6).fuerzaGolpe);
        
        
    }
    
        @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        
        cuerda_E.addObserver(o);
        cuerda_A.addObserver(o);
        cuerda_D.addObserver(o);
        cuerda_G.addObserver(o);
        cuerda_B.addObserver(o);
        cuerda_e.addObserver(o);
        
    }
    
    
    
    
}
