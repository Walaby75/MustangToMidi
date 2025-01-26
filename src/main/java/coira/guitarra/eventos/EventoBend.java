/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoBend extends Evento{
    int tonos;


    public EventoBend(int tonos, int cuerda) {
        super(cuerda);
        this.tonos = tonos;
    }

    
    
    
}
