/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.Midi;

/**
 *
 * @author Administrador
 */
public class NotaMidi {
    int disp;
    int nota;
    int canal;
    int fuerza;

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }

    
    
    
    /*
    public NotaMidi(int nota, int canal, int fuerza) {
        this.nota = nota;
        this.canal = canal;
        this.fuerza = fuerza;
        
    }
    */
    
    public NotaMidi(int disp,int nota, int canal, int fuerza,  int minimo) {
        this.nota = nota;
        this.canal = canal;
        this.disp = disp;
        fuerza=fuerza<0?fuerza+128:fuerza;
        if (minimo>0){
            fuerza=fuerza>=minimo?fuerza:minimo;
        }
        this.fuerza = fuerza;
        
    }
    
    public NotaMidi(int disp,int nota, int canal, int fuerza,  int minimo, int maximo) {
        this.nota = nota;
        this.canal = canal;
        this.disp = disp;
        fuerza=fuerza<0?fuerza+128:fuerza;
        if (minimo>0){
            fuerza=fuerza>=minimo?fuerza:minimo;
            
        }
        if (maximo>0){
            fuerza=fuerza<=maximo?fuerza:maximo;
            
        }
        this.fuerza = fuerza;
        
    }
    
    
    
}
