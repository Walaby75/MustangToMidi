/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

import coira.Midi.NotaMidi;

/**
 *
 * @author Administrador
 */
public class OrdenSlideDown extends OrdenGuitarra{

private int variacion;
private int canal;
private boolean sigue;
private int nota;
private int disp;

    public int getVariacion() {
        return variacion;
    }

    public void setVariacion(int variacion) {
        this.variacion = variacion;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public boolean isSigue() {
        return sigue;
    }

    public void setSigue(boolean sigue) {
        this.sigue = sigue;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }
    
    
    

    public OrdenSlideDown(int disp,int variacion, int canal, boolean sigue, int nota) {
        this.disp = disp;
        this.variacion = variacion;
        this.canal = canal;
        this.sigue = sigue;
        this.nota = nota;
    }

        
    
    
}
