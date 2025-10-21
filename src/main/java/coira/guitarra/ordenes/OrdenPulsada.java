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
public class OrdenPulsada extends OrdenGuitarra{

    int fuerza;
    int nota;

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public OrdenPulsada(int fuerza, int nota, String origen, Cuerda cuerda) {
        this.fuerza = fuerza;
        this.nota = nota;
        this.origen = origen;
        this.cuerda = cuerda;
    }
    
    
    
}
