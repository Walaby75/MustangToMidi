/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

/**
 *
 * @author Administrador
 */
public class OrdenSlide {
    int nota;
    int canal;
    int disp;

    public OrdenSlide(int nota, int canal, int disp) {
        this.nota = nota;
        this.canal = canal;
        this.disp = disp;
    }

    public int getNota() {
        return nota;
    }

    public int getCanal() {
        return canal;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }
    
    
    
}
