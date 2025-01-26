/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoLegato extends  Evento{
    
    int variacion;
    boolean slide;

    public int getVariacion() {
        return variacion;
    }

    public void setVariacion(int variacion) {
        this.variacion = variacion;
    }

    public boolean isSlide() {
        return slide;
    }

    public void setSlide(boolean slide) {
        this.slide = slide;
    }

    public EventoLegato(int variacion, boolean slide, int cuerda) {
        super(cuerda);
        this.variacion = variacion;
        this.slide = slide;
    }

    public EventoLegato(int variacion, boolean slide) {
        this.variacion = variacion;
        this.slide = slide;
    }
    
    
    
    
}
