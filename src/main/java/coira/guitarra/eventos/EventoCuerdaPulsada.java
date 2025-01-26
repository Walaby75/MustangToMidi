/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoCuerdaPulsada extends Evento{
    int fuerza;
    
    public EventoCuerdaPulsada(int cuerda, int fuerza) {
        super(cuerda);
        this.fuerza=fuerza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }
    
    
    
}
