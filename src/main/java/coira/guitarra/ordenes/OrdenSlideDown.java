/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

import coira.Midi.NotaMidi;

/**
 *
 * @author Administrador
 */
public class OrdenSlideDown extends OrdenLegato{

    int variacion;
    int fuerza;

    public int getVariacion() {
        return variacion;
    }

    public void setVariacion(int variacion) {
        this.variacion = variacion;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public OrdenSlideDown(int variacion, int fuerza, String origen) {
        this.variacion = variacion;
        this.fuerza = fuerza;
        this.origen = origen;
    }
    
    
    
}
