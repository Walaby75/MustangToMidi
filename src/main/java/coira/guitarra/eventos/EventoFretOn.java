/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoFretOn extends Evento{
    
    int traste;

    public EventoFretOn(int traste, int cuerda) {
        super(cuerda);
        this.traste = traste;
    }

    public int getTraste() {
        return traste;
    }

    public void setTraste(int traste) {
        this.traste = traste;
    }
    
    
    
}
