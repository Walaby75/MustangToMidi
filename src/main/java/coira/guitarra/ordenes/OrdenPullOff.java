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
public class OrdenPullOff extends OrdenLegato{

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

    public OrdenPullOff(int variacion, int fuerza, String origen, Cuerda cuerda, int tonoOrigen, int tonoDestino) {
        this.variacion = variacion;
        this.fuerza = fuerza;
        this.origen = origen;
        this.cuerda = cuerda;
        this.tonoOrigen = tonoOrigen;
        this.tonoDestino = tonoDestino;
    }

    
    

}
