/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

import coira.guitarra.Cuerda;

/**
 *
 * @author Administrador
 */
public class OrdenGuitarra {
    protected String origen;
    protected Cuerda cuerda;

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Cuerda getCuerda() {
        return cuerda;
    }

    public void setCuerda(Cuerda cuerda) {
        this.cuerda = cuerda;
    }
    
    
    
    
    public OrdenGuitarra(){
        
        System.out.println(this.getClass().getName());
    }
    
}
