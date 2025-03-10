/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coira.salida;

import coira.guitarra.eventos.EventoTrasteCuerda;
import coira.guitarra.ordenes.OrdenGuitarra;

/**
 *
 * @author Usuario
 */
public class GuitarraMustangSalida {
    private static GuitarraMustangSalida instance;
    
    public static GuitarraMustangSalida getInstance(){
        if (instance == null){
            instance = new GuitarraMustangSalida();
        }
        return instance;
    }
    
    public void ejecutar(OrdenGuitarra orden){
        System.out.println("Orden recibida : "+orden.getClass().getCanonicalName());
    }

    public void ejecutar(EventoTrasteCuerda orden){
        System.out.println("Orden recibida : "+orden.getClass().getCanonicalName());
    }

    
}
