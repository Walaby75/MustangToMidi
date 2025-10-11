/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

/**
 *
 * @author Administrador
 */
public class OrdenGuitarra {
    protected String origen;

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
    
    
    
    public OrdenGuitarra(){
        
        System.out.println(this.getClass().getName());
    }
    
}
