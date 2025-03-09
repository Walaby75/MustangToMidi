/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.entrada;


import coira.guitarra.ControlCuerdas;
import coira.guitarra.DataCuerda;
import coira.properties.GeneralProperties;
import coira.properties.GuitarProperties;
import java.util.HashMap;
import java.util.Map;
import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesSpecification;

/**
 *
 * @author Administrador
 */
public class GuitarraMustangEntrada{
    

    int largo =32;
    byte[] readData = new byte[largo];
    byte[] oldReadData = new byte[largo];
    HidDevice dev;

    int string_E, string_A,string_D,string_G,string_B,string_e;

    
    ControlCuerdas controlCuerdas;
    
    boolean boton1=false;
    boolean boton2=false;
    boolean botonA=false;
    boolean botonB=false;
    

    public GuitarraMustangEntrada(){
        int vendorId = 7085;  // Reemplazar con el Vendor ID real
        int productId = 13360; // Reemplazar con el Product ID real


        // Crear instancia del servicio HID
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
        HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);
        
        dev = hidServices.getHidDevice(vendorId, productId, null);
        
        controlCuerdas = new ControlCuerdas();
    }

    
    
    
    private void detectoCambio(byte[] data, byte[] olddata){
        String quinto = Integer.toHexString(data[5] & 0xff);
        int iquinto = Integer.parseInt(quinto, 16);
        String sexto = Integer.toHexString(data[6] & 0xff);
        int isexto = Integer.parseInt(sexto, 16);
        String septimo = Integer.toHexString(data[7] & 0xff);
        int iseptimo = Integer.parseInt(septimo, 16);
        String octavo = Integer.toHexString(data[8] & 0xff);
        int ioctavo = Integer.parseInt(octavo, 16);
        
        string_E = (iquinto % 32)%32;
        string_A = ((iquinto - string_E)/32 +((isexto % 4)*8))%32;
        string_D = ((isexto - (isexto % 4))/4)%32;
        string_G = (iseptimo % 32)%32;
        string_B = ((iseptimo - string_G)/32 +((ioctavo % 4)*8))%32;
        string_e = ((ioctavo - (ioctavo % 4))/4)%32;
        boolean cambio=false;
        for (int i=4;i<9;i++){
            if (olddata[i]!=data[i]){
                olddata[i]=data[i];
                cambio=true;
            }
            
        }
        
        if (cambio){
            System.out.println(string_E+" - "+string_A+" - "+string_D+" - "+string_G+" - "+string_B+" - "+string_e+" - ");
        }
        Map<Integer,DataCuerda> cuerdas = new HashMap<>();
        cuerdas.put(6,new DataCuerda(string_E, data[9]));
        cuerdas.put(5,new DataCuerda(string_A, data[10]));
        cuerdas.put(4,new DataCuerda(string_D, data[11]));
        cuerdas.put(3,new DataCuerda(string_G, data[12]));
        cuerdas.put(2,new DataCuerda(string_B, data[13]));
        cuerdas.put(1,new DataCuerda(string_e, data[14]));
        
        controlCuerdas.detectoCambio(cuerdas);
        
        
        interpretoByte1(data,olddata);
        interpretoByte0(data,olddata);
        interpretoByte2(data,olddata);
    }

    public void interpretoByte1(byte[] data, byte[] olddata){
        if (data[1]!=olddata[1]){
            System.out.println("Byte1 "+data[1] );
            olddata[1]=data[1];
            switch (data[1]) {
                case 16 -> {
                    // enmudece todo

                }
                case 1 -> {
                    // baja un semitono
                }
                case 17 -> {
                    //sube un semitono 17
                }
                case 18 -> {
                    //vuelve tono al inicio 18
                }
                case 3 -> {
                    //sube fuerzas topes 3
                }
                case 2 -> {
                    //baja fuerzas tope 2
                }
                case 19 -> {
                    //vuelve fuerzas al origen
                }
                default -> {
                }
            }
            
        }
        
    }
    
    
    public void interpretoByte0(byte[] data, byte[] olddata){
        int auxRes=0;
        int  auxValor = data[0];
        if (data[0]!=olddata[0]){
            auxRes=auxValor % 2;
            boton1 = auxRes   == 1;
            auxValor-=auxRes;
            auxRes=auxValor % 4;
            botonA =  auxRes == 2;
            auxValor-=auxRes;
            auxRes=auxValor % 8;
            botonB =  auxRes == 4;
            auxValor-=auxRes;
            boton2=auxValor==8;
            if (boton1){
            }
            if (boton2){

            }
            if (botonA){
            }
            if (botonB){
            }

        }
        olddata[0]=data[0];

    }
    
    public void interpretoByte2(byte[] data, byte[] olddata){
        //pad
        if (data[2]!=olddata[2]){
            System.out.println("Byte2 "+data[2] );
            if (data[2]==4){
                //hacia las cuerdas
            }
            if (data[2]==8){
               //pad off 
            }
            if (data[2]==6){
                // hacia el cuello
            }
            if (data[2]==0){
                // aleja las cuerdas
            }
            if (data[2]==2){
                // aleja del cuello
            }

            
        }
        olddata[2]=data[2];
    }
    
    public void run() {
            try {
        
                while (true){
                        int bytesRead = dev.read(readData, 1000);
                        detectoCambio(readData, oldReadData);

                }
            } catch (Exception e) {
                  e.printStackTrace();
            } finally {
                    // Cerrar el dispositivo al finalizar
                    dev.close();
                    System.out.println("Dispositivo cerrado.");
            }
    }
    


    

}
