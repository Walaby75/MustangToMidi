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
public class OrdenStopSlide extends OrdenGuitarra{

private int variacion;
private int nota;
private int canal;
private String disp;
private boolean sigue;

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

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    
    
    public OrdenStopSlide(String disp,int variacion, int canal, boolean sigue, int nota) {
        this.disp = disp;
        this.variacion = variacion;
        this.canal = canal;
        this.sigue = sigue;
        this.nota = nota;
    }

    
    
}
