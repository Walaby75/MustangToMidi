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
public class OrdenApagadoCuerdaDelay extends OrdenGuitarra{
        String dispositivo = null;
        int canal = -10;
        NotaMidi nota;
        

    public NotaMidi getNota() {
        return nota;
    }

    public void setNota(NotaMidi nota) {
        this.nota = nota;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    
    
    
    public OrdenApagadoCuerdaDelay(String dispositivo ,NotaMidi nota) {
        this.nota = nota;
        this.dispositivo = dispositivo;
    }
        
    public OrdenApagadoCuerdaDelay(String dispositivo ,int canal) {
        this.canal = canal;
        this.dispositivo = dispositivo;
    }  
    
    
}
