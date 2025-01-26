/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coira.guitartomidi;
import java.util.Arrays;
import org.hid4java.*;
import org.hid4java.event.*;

public class USBHIDReader {
    public static void main(String[] args) {
        // Especifica el Vendor ID y Product ID de tu dispositivo USB HID
        /*
        Vendor ID (VID): 7085
Product ID (PID): 13360
        */
        
        int vendorId = 7085;  // Reemplazar con el Vendor ID real
        int productId = 13360; // Reemplazar con el Product ID real


        // Crear instancia del servicio HID
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
        HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);

        System.out.println("Buscando dispositivos USB HID...");

        // Buscar el dispositivo específico
        HidDevice device = hidServices.getHidDevice(vendorId, productId, null);

        if (device != null) {
            System.out.println("Dispositivo encontrado: " + device.getProduct());

            // Intentar abrir el dispositivo
            if (device.open()) {
                System.out.println("Dispositivo abierto correctamente.");

                try {
                    byte[] previousData = new byte[64];  // Buffer para comparar
                    byte[] dataBuffer = new byte[64];    // Buffer de lectura

                    System.out.println("Leyendo datos del dispositivo (presiona Ctrl+C para salir)...");

                    while (true) {
                        // Leer datos con un tiempo de espera de 1 segundo (1000 ms)
                        int bytesRead = device.read(dataBuffer, 1000);

                        if (bytesRead > 0) {
                            // Comparar con la lectura anterior
                            if (!Arrays.equals(previousData, dataBuffer)) {
                                System.out.print("Nueva lectura de datos: ");
                                for (int i = 0; i < bytesRead; i++) {
                                    System.out.print( dataBuffer[i]+" ");
                                }
                                System.out.println();

                                // Actualizar la lectura anterior
                                System.arraycopy(dataBuffer, 0, previousData, 0, bytesRead);
                            }
                        } else {
                            System.out.println("No se recibieron datos...");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Cerrar el dispositivo al finalizar
                    device.close();
                    System.out.println("Dispositivo cerrado.");
                }
            } else {
                System.out.println("No se pudo abrir el dispositivo.");
            }
        } else {
            System.out.println("No se encontró el dispositivo con Vendor ID: " + vendorId + " y Product ID: " + productId);
        }
    }
}
