/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoPullOff extends Evento{
    int tonos;

    public EventoPullOff(int tonos, int cuerda) {
        super(cuerda);
        this.tonos = tonos;
    }

    public int getTonos() {
        return tonos;
    }

    public void setTonos(int tonos) {
        this.tonos = tonos;
    }
    
    
    
}
