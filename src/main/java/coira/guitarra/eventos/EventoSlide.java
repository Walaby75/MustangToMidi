/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoSlide extends Evento{
    int tonos;


    public EventoSlide(int tonos, int cuerda) {
        super(cuerda);
        this.tonos = tonos;
    }

    
    
    
}
