/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.eventos;

/**
 *
 * @author Administrador
 */
public class EventoTrasteCuerda extends Evento{
    int traste;
    int canal;
    String origen;
  
            

    public EventoTrasteCuerda(int traste, int canal, int cuerda, String origen) {
        super(cuerda);
        this.traste = traste;
        this.canal =canal;
        this.origen = origen;

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

    
    
    
}
