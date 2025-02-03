/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra;

/**
 *
 * @author Usuario
 */
public class DataCuerda {
    int traste,  fuerzaGolpe;

    public int getTraste() {
        return traste;
    }

    public void setTraste(int traste) {
        this.traste = traste;
    }

    public int getFuerzaGolpe() {
        return fuerzaGolpe;
    }

    public void setFuerzaGolpe(int fuerzaGolpe) {
        this.fuerzaGolpe = fuerzaGolpe;
    }

    public DataCuerda(int traste, int fuerzaGolpe) {
        this.traste = traste;
        this.fuerzaGolpe = fuerzaGolpe;
    }
    
    
    
}
