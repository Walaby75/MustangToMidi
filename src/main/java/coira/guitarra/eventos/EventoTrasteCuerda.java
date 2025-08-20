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
  
            

    public EventoTrasteCuerda(int traste, int canal, int cuerda) {
        super(cuerda);
        this.traste = traste;
        this.canal =canal;

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

    
    
    
}
