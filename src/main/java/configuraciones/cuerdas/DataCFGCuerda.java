/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuraciones.cuerdas;

/**
 *
 * @author Usuario
 */
public class DataCFGCuerda {
    
    int tonoInicial = -1;
    
    String modoSustain = "L";
    // Tramos
    // Lineal
    // Exponencial
    int tramos = 5;
     

    public int getTonoInicial() {
        return tonoInicial;
    }


    public void setTonoInicial(int tonoInicial) {
        this.tonoInicial = tonoInicial;
    }

    public DataCFGCuerda(int tonoInicial, String modoSustain, int tramos) {
        this.tonoInicial = tonoInicial;
        this.modoSustain = modoSustain;
        this.tramos = tramos;
    }

    public String getModoSustain() {
        return modoSustain;
    }

    public void setModoSustain(String modoSustain) {
        this.modoSustain = modoSustain;
    }

    public int getTramos() {
        return tramos;
    }

    public void setTramos(int tramos) {
        this.tramos = tramos;
    }


    
    
    
    
}
