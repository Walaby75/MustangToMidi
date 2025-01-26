/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

/**
 *
 * @author Administrador
 */
public class OrdenBendUp extends OrdenGuitarra{
    private int tones = 0;
    private int disp = 0;


    public int getTones() {
        return tones;
    }

    public void setTones(int tones) {
        this.tones = tones;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }

    
    
    public OrdenBendUp() {
    }

    public OrdenBendUp(  int disp, int tones) {
        this.tones = tones;
        this.disp = disp;
    }
    
    
    
    
}
