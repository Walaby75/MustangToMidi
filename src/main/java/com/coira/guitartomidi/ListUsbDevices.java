/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coira.guitartomidi;

import org.hid4java.*;

public class ListUsbDevices {
    public static void main(String[] args) {
        try {
            // Inicializar el servicio HID
            HidServices hidServices = HidManager.getHidServices();
            hidServices.start();

            System.out.println("Buscando dispositivos HID...");

            // Recorrer todos los dispositivos detectados
            for (HidDevice device : hidServices.getAttachedHidDevices()) {
                System.out.println("---------------------------------");
                System.out.println("Fabricante: " + device.getManufacturer());
                System.out.println("Producto: " + device.getProduct());
                System.out.println("Vendor ID (VID): "+ device.getVendorId());
                System.out.println("Product ID (PID): "+ device.getProductId());
                System.out.println("Número de serie: " + device.getSerialNumber());
                System.out.println("Ruta del dispositivo: " + device.getPath());
                System.out.println("Lanzamiento: " + device.getReleaseNumber());
                System.out.println("Uso de página: " + device.getUsagePage());
                System.out.println("Uso: " + device.getUsage());

                
           }

            hidServices.shutdown();
        } catch (HidException e) {
            e.printStackTrace();
        }
    }
}
