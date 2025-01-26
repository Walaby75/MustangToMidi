/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoSlideDown extends Evento{
    int tonos;
    int disp;
    
    public EventoSlideDown(int cuerda, int tonos, int disp) {
        super(cuerda);
        this.tonos=tonos;
        this.disp=disp;
    }

    public int getTonos() {
        return tonos;
    }

    public void setTonos(int tonos) {
        this.tonos = tonos;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }
    
    
}
