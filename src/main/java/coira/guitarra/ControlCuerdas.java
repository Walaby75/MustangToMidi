/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra;

import coira.properties.GeneralProperties;
import coira.properties.GuitarProperties;
import configuraciones.cuerdas.CFGCuerdas;
import configuraciones.salida.CFGSalidas;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class ControlCuerdas  {
    
    Cuerda cuerda_6 ;
    Cuerda cuerda_5 ;
    Cuerda cuerda_4 ;
    Cuerda cuerda_3 ;
    Cuerda cuerda_2 ;
    Cuerda cuerda_1 ;
    
    GuitarProperties guitarProperties;
    GeneralProperties prop;

    public Cuerda getCuerda_6() {
        return cuerda_6;
    }

    public void setCuerda_6(Cuerda cuerda_6) {
        this.cuerda_6 = cuerda_6;
    }

    public Cuerda getCuerda_5() {
        return cuerda_5;
    }

    public void setCuerda_5(Cuerda cuerda_5) {
        this.cuerda_5 = cuerda_5;
    }

    public Cuerda getCuerda_4() {
        return cuerda_4;
    }

    public void setCuerda_4(Cuerda cuerda_4) {
        this.cuerda_4 = cuerda_4;
    }

    public Cuerda getCuerda_3() {
        return cuerda_3;
    }

    public void setCuerda_3(Cuerda cuerda_3) {
        this.cuerda_3 = cuerda_3;
    }

    public Cuerda getCuerda_2() {
        return cuerda_2;
    }

    public void setCuerda_2(Cuerda cuerda_2) {
        this.cuerda_2 = cuerda_2;
    }

    public Cuerda getCuerda_1() {
        return cuerda_1;
    }

    public void setCuerda_1(Cuerda cuerda_1) {
        this.cuerda_1 = cuerda_1;
    }


    public GuitarProperties getGuitarProperties() {
        return guitarProperties;
    }

    public void setGuitarProperties(GuitarProperties guitarProperties) {
        this.guitarProperties = guitarProperties;
        cuerda_6.setGuitarProperties(guitarProperties);
        cuerda_6.setPrimerNota(guitarProperties.getString_initial_value_lowE());
        cuerda_5.setGuitarProperties(guitarProperties);
        cuerda_5.setPrimerNota(guitarProperties.getString_initial_value_A());
        cuerda_4.setGuitarProperties(guitarProperties);
        cuerda_4.setPrimerNota(guitarProperties.getString_initial_value_D());
        cuerda_3.setGuitarProperties(guitarProperties);
        cuerda_3.setPrimerNota(guitarProperties.getString_initial_value_G());
        cuerda_2.setGuitarProperties(guitarProperties);
        cuerda_2.setPrimerNota(guitarProperties.getString_initial_value_B());
        cuerda_1.setGuitarProperties(guitarProperties);
        cuerda_1.setPrimerNota(guitarProperties.getString_initial_value_highE());
    }

    /*
    public ControlCuerdas(GuitarProperties gp, GeneralProperties prop) {
    
        guitarProperties = gp;
        this.prop = prop;
        cuerda_6 = new Cuerda(guitarProperties.getString_initial_value_lowE(), guitarProperties.getString_initial_channel_lowE(),prop.getPropertyAsInt("midi.port.String.lowE", guitarProperties.getString_initial_channel_lowE()),guitarProperties.getString_cc_selection_value_lowE(),guitarProperties.getString_key_selection_value_lowE());
        cuerda_5 = new Cuerda(guitarProperties.getString_initial_value_A(), guitarProperties.getString_initial_channel_A(),prop.getPropertyAsInt("midi.port.String.A", guitarProperties.getString_initial_channel_A()),guitarProperties.getString_cc_selection_value_A(),guitarProperties.getString_key_selection_value_A());
        cuerda_4 = new Cuerda(guitarProperties.getString_initial_value_D(), guitarProperties.getString_initial_channel_D(),prop.getPropertyAsInt("midi.port.String.D", guitarProperties.getString_initial_channel_D()),guitarProperties.getString_cc_selection_value_D(),guitarProperties.getString_key_selection_value_D());
        cuerda_3 = new Cuerda(guitarProperties.getString_initial_value_G(), guitarProperties.getString_initial_channel_G(),prop.getPropertyAsInt("midi.port.String.G", guitarProperties.getString_initial_channel_G()),guitarProperties.getString_cc_selection_value_G(),guitarProperties.getString_key_selection_value_G());
        cuerda_2 = new Cuerda(guitarProperties.getString_initial_value_B(), guitarProperties.getString_initial_channel_B(),prop.getPropertyAsInt("midi.port.String.B", guitarProperties.getString_initial_channel_B()),guitarProperties.getString_cc_selection_value_B(),guitarProperties.getString_key_selection_value_B());
        cuerda_1 = new Cuerda(guitarProperties.getString_initial_value_highE(), guitarProperties.getString_initial_channel_highE(),prop.getPropertyAsInt("midi.port.String.highE", guitarProperties.getString_initial_channel_highE()),guitarProperties.getString_cc_selection_value_highE(),guitarProperties.getString_key_selection_value_highE());
        cuerda_6.setGuitarProperties(guitarProperties);
        cuerda_5.setGuitarProperties(guitarProperties);
        cuerda_4.setGuitarProperties(guitarProperties);
        cuerda_3.setGuitarProperties(guitarProperties);
        cuerda_2.setGuitarProperties(guitarProperties);
        cuerda_1.setGuitarProperties(guitarProperties);
        addObserver(cuerda_6);
        addObserver(cuerda_5);
        addObserver(cuerda_4);
        addObserver(cuerda_3);
        addObserver(cuerda_2);
        addObserver(cuerda_1);
    
    }*/
    
    public void detectoCambio(Map<Integer,DataCuerda> cuerdas ){
        
        cuerda_6.interpretoEvento(cuerdas.get(6).traste,cuerdas.get(6).fuerzaGolpe);
        cuerda_5.interpretoEvento(cuerdas.get(5).traste,cuerdas.get(5).fuerzaGolpe);
        cuerda_4.interpretoEvento(cuerdas.get(4).traste,cuerdas.get(4).fuerzaGolpe);
        cuerda_3.interpretoEvento(cuerdas.get(3).traste,cuerdas.get(3).fuerzaGolpe);
        cuerda_2.interpretoEvento(cuerdas.get(2).traste,cuerdas.get(2).fuerzaGolpe);
        cuerda_1.interpretoEvento(cuerdas.get(1).traste,cuerdas.get(1).fuerzaGolpe);
        
        
    }
/*    
    public void addObserver(Observer o){
        super.addObserver(o);
        
        cuerda_6.addObserver(o);
        cuerda_5.addObserver(o);
        cuerda_4.addObserver(o);
        cuerda_3.addObserver(o);
        cuerda_2.addObserver(o);
        cuerda_1.addObserver(o);
        
    }*/
    
    
    public ControlCuerdas(){
        if (CFGCuerdas.getInstance().getCuerdas().get(6) != null)
            cuerda_6 = new Cuerda(CFGCuerdas.getInstance().getCuerdas().get(6).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(6).getCanal(),CFGSalidas.getInstance().getSalidas().get(6).getPuerto(),6);
        if (CFGCuerdas.getInstance().getCuerdas().get(5) != null)
            cuerda_5 = new Cuerda(CFGCuerdas.getInstance().getCuerdas().get(5).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(5).getCanal(),CFGSalidas.getInstance().getSalidas().get(5).getPuerto(),5);
        if (CFGCuerdas.getInstance().getCuerdas().get(4) != null)
            cuerda_4 = new Cuerda(CFGCuerdas.getInstance().getCuerdas().get(4).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(4).getCanal(),CFGSalidas.getInstance().getSalidas().get(4).getPuerto(),4);
        if (CFGCuerdas.getInstance().getCuerdas().get(3) != null)
            cuerda_3 = new Cuerda(CFGCuerdas.getInstance().getCuerdas().get(3).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(3).getCanal(),CFGSalidas.getInstance().getSalidas().get(3).getPuerto(),3);
        if (CFGCuerdas.getInstance().getCuerdas().get(2) != null)
            cuerda_2 = new Cuerda(CFGCuerdas.getInstance().getCuerdas().get(2).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(2).getCanal(),CFGSalidas.getInstance().getSalidas().get(2).getPuerto(),2);
        if (CFGCuerdas.getInstance().getCuerdas().get(1) != null)
            cuerda_1 = new Cuerda(CFGCuerdas.getInstance().getCuerdas().get(1).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(1).getCanal(),CFGSalidas.getInstance().getSalidas().get(1).getPuerto(),1);
        
    }
    
}
