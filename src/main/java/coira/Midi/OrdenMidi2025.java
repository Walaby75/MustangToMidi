/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coira.Midi;

import coira.guitarra.Cuerda;

/**
 *
 * @author Usuario
 */
public class OrdenMidi2025 implements Comparable<OrdenMidi2025> {
    int orden;
    Cuerda cuerda;
    String puerto;
    Integer canal;
    int comando;
    int nota;
    int fuerza;
    int delay;

    @Override
    public int compareTo(OrdenMidi2025 o) {
        return Integer.compare(this.orden, o.orden);
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Cuerda getCuerda() {
        return cuerda;
    }

    public void setCuerda(Cuerda cuerda) {
        this.cuerda = cuerda;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public Integer getCanal() {
        return canal;
    }

    public void setCanal(Integer canal) {
        this.canal = canal;
    }

    public int getComando() {
        return comando;
    }

    public void setComando(int comando) {
        this.comando = comando;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public OrdenMidi2025(int orden, Cuerda cuerda, String puerto, Integer canal, int comando, int nota, int fuerza, int delay) {
        this.orden = orden;
        this.cuerda = cuerda;
        this.puerto = puerto;
        this.canal = canal;
        this.comando = comando;
        this.nota = nota;
        this.fuerza = fuerza;
        this.delay = delay;
    }
    
    public OrdenMidi2025(){
        
    }
    
    
    
}
