/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

import coira.Midi.NotaMidi;
import coira.guitarra.Cuerda;

/**
 *
 * @author Administrador
 */
public class OrdenHammerOn extends OrdenLegato{

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

    public OrdenHammerOn(int variacion, int fuerza, String origen, Cuerda cuerda) {
        this.variacion = variacion;
        this.fuerza = fuerza;
        this.origen = origen;
        this.cuerda = cuerda;
    }
    
    

}
