/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.guitarra.ordenes;

/**
 *
 * @author Administrador
 */
public class OrdenCambioCuerda extends OrdenGuitarra{
    boolean activa;
    boolean cc;
    int dispositivo;
    int canal;
    int valor;

    public OrdenCambioCuerda(boolean activa, boolean cc, int dispositivo, int canal, int valor) {
        this.activa = activa;
        this.cc = cc;
        this.dispositivo = dispositivo;
        this.canal = canal;
        this.valor = valor;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean isCc() {
        return cc;
    }

    public void setCc(boolean cc) {
        this.cc = cc;
    }

    public int getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(int dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    
}
