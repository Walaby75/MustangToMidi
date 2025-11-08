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
    
    static ControlCuerdas instance  = null;
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

    }

    
    public void detectoCambio(Map<Integer,DataCuerda> cuerdas ){
        
        cuerda_6.interpretoEvento(cuerdas.get(6).traste,cuerdas.get(6).fuerzaGolpe);
        cuerda_5.interpretoEvento(cuerdas.get(5).traste,cuerdas.get(5).fuerzaGolpe);
        cuerda_4.interpretoEvento(cuerdas.get(4).traste,cuerdas.get(4).fuerzaGolpe);
        cuerda_3.interpretoEvento(cuerdas.get(3).traste,cuerdas.get(3).fuerzaGolpe);
        cuerda_2.interpretoEvento(cuerdas.get(2).traste,cuerdas.get(2).fuerzaGolpe);
        cuerda_1.interpretoEvento(cuerdas.get(1).traste,cuerdas.get(1).fuerzaGolpe);
        
        
    }

    public static ControlCuerdas getInstance(){
        if (instance == null){
            instance = new ControlCuerdas();
        }
        
        return instance;
    }
    
    private ControlCuerdas(){
        if (CFGCuerdas.getInstance().getCuerdas().get(6) != null){
            cuerda_6 = new Cuerda("cuerda_6",CFGCuerdas.getInstance().getCuerdas().get(6).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(6).getCanal(),CFGSalidas.getInstance().getSalidas().get(6).getPuerto(),6);
        
        }
        if (CFGCuerdas.getInstance().getCuerdas().get(5) != null)
            cuerda_5 = new Cuerda("cuerda_5",CFGCuerdas.getInstance().getCuerdas().get(5).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(5).getCanal(),CFGSalidas.getInstance().getSalidas().get(5).getPuerto(),5);
        if (CFGCuerdas.getInstance().getCuerdas().get(4) != null)
            cuerda_4 = new Cuerda("cuerda_4",CFGCuerdas.getInstance().getCuerdas().get(4).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(4).getCanal(),CFGSalidas.getInstance().getSalidas().get(4).getPuerto(),4);
        if (CFGCuerdas.getInstance().getCuerdas().get(3) != null)
            cuerda_3 = new Cuerda("cuerda_3",CFGCuerdas.getInstance().getCuerdas().get(3).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(3).getCanal(),CFGSalidas.getInstance().getSalidas().get(3).getPuerto(),3);
        if (CFGCuerdas.getInstance().getCuerdas().get(2) != null)
            cuerda_2 = new Cuerda("cuerda_2",CFGCuerdas.getInstance().getCuerdas().get(2).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(2).getCanal(),CFGSalidas.getInstance().getSalidas().get(2).getPuerto(),2);
        if (CFGCuerdas.getInstance().getCuerdas().get(1) != null)
            cuerda_1 = new Cuerda("cuerda_1",CFGCuerdas.getInstance().getCuerdas().get(1).getTonoInicial(), CFGSalidas.getInstance().getSalidas().get(1).getCanal(),CFGSalidas.getInstance().getSalidas().get(1).getPuerto(),1);
        
    }
    
}
