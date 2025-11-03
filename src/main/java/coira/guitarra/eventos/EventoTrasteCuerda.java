/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

import coira.guitarra.Cuerda;

/**
 *
 * @author Administrador
 */
public class EventoTrasteCuerda extends Evento{
    int traste;
    int canal;
    String origen;
    Cuerda cuerdaObjeto;
    int trasteAnterior;
            
  
            

    public EventoTrasteCuerda(int traste, int trasteAnterior, int canal, int cuerda, String origen, Cuerda cuerdaObjeto) {
        super(cuerda);
        this.traste = traste;
        this.canal =canal;
        this.origen = origen;
        this.cuerdaObjeto = cuerdaObjeto;
        this.trasteAnterior = trasteAnterior;

    }

    public int getTraste() {
        return traste;
    }

    public void setTraste(int traste) {
        this.traste = traste;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Cuerda getCuerdaObjeto() {
        return cuerdaObjeto;
    }

    public void setCuerdaObjeto(Cuerda cuerdaObjeto) {
        this.cuerdaObjeto = cuerdaObjeto;
    }

    public int getTrasteAnterior() {
        return trasteAnterior;
    }

    public void setTrasteAnterior(int trasteAnterior) {
        this.trasteAnterior = trasteAnterior;
    }

    public int getCuerda() {
        return cuerda;
    }

    public void setCuerda(int cuerda) {
        this.cuerda = cuerda;
    }

    
    
    
}
