package com.coira.guitartomidi;


import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceHandle;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;


public class USBReader {
    public static void main(String[] args) {
        // Inicializar la biblioteca USB
        Context context = new Context();
        int result = LibUsb.init(context);
        if (result != LibUsb.SUCCESS) {
            System.out.println("Error al inicializar la biblioteca USB");
            return;
        }

        // Obtener la lista de dispositivos USB
        DeviceList deviceList = new DeviceList();
        result = LibUsb.getDeviceList(context, deviceList);
        if (result != LibUsb.SUCCESS) {
            System.out.println("Error al obtener lista de dispositivos USB");
            System.out.println("Error al obtener lista de dispositivos USB: " + LibUsb.strError(result));

            return;
        } 
        

        // Iterar sobre los dispositivos USB encontrados
        for (Device device : deviceList) {
            DeviceDescriptor descriptor = new DeviceDescriptor();
            result = LibUsb.getDeviceDescriptor(device, descriptor);
            if (result == LibUsb.SUCCESS) {
                System.out.println("Dispositivo USB encontrado: "
                        + descriptor.idVendor() + ":" + descriptor.idProduct());

                // Obtener el nombre del dispositivo (descripción del producto)
                String deviceName = getDeviceName(device);
                System.out.println("Nombre del dispositivo: " + deviceName);
            }
        }

        // Limpiar recursos
        LibUsb.freeDeviceList(deviceList, true);
        LibUsb.exit(context);
    }

    // Método para obtener el nombre del dispositivo (descripción del producto)
    public static String getDeviceName(Device device) {
        byte[] buffer = new byte[256];
        StringBuffer sbuff = new StringBuffer();

        // Abrir un handle del dispositivo
        DeviceHandle handle = new DeviceHandle();
        int result = LibUsb.open(device, handle);
        if (result != LibUsb.SUCCESS) {
            return "No se pudo abrir el dispositivo";
        }

        // Obtener el descriptor de cadena (producto)
        result = LibUsb.getStringDescriptorAscii(handle, (byte) 2, sbuff);

        if (result >= 0) {
            LibUsb.close(handle);  // Cerrar el handle después de usarlo
            return sbuff.toString(); // Convertir el buffer a string
        } else {
            LibUsb.close(handle);  // Cerrar el handle después de usarlo
            return "Desconocido"; // En caso de error o si no se puede obtener el nombre
        }
    }
}
