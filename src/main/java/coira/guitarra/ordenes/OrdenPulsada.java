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
public class OrdenPulsada extends OrdenGuitarra{

        NotaMidi nota;
        int cc;
        int key_string;

    public NotaMidi getNota() {
        return nota;
    }

    public void setNota(NotaMidi nota) {
        this.nota = nota;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public int getKey_string() {
        return key_string;
    }

    public void setKey_string(int key_string) {
        this.key_string = key_string;
    }

    
    public OrdenPulsada(NotaMidi nota, int cc, int key_string) {
        this.nota = nota;
        this.cc = cc;
        this.key_string=key_string;
    }
        
        
    
    
}
