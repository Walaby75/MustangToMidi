/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.USB;


import coira.Midi.NotaMidi;
import coira.guitarra.ControlCuerdas;
import coira.guitarra.Cuerda;
import coira.guitarra.DataCuerda;
import coira.guitarra.ordenes.OrdenApagado;
import coira.guitarra.ordenes.OrdenBajoFuerza;
import coira.guitarra.ordenes.OrdenToStrings;
import coira.guitarra.ordenes.OrdenBoton1;
import coira.guitarra.ordenes.OrdenBoton2;
import coira.guitarra.ordenes.OrdenBotonA;
import coira.guitarra.ordenes.OrdenBotonB;
import coira.guitarra.ordenes.OrdenBotonMas;
import coira.guitarra.ordenes.OrdenBotonMasMenos;
import coira.guitarra.ordenes.OrdenBotonMenos;
import coira.guitarra.ordenes.OrdenMovePadOff;
import coira.guitarra.ordenes.OrdenReseteoFuerza;
import coira.guitarra.ordenes.OrdenSuboFuerza;
import coira.guitarra.ordenes.OrdenSwitchOnOffSlide;
import coira.guitarra.ordenes.OrdenToNeck;
import coira.properties.GeneralProperties;
import coira.properties.GuitarProperties;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesSpecification;

/**
 *
 * @author Administrador
 */
public class EntradaGuitarra extends Observable implements Runnable{
    
    GuitarProperties guitarProperties;
    int largo =32;
    byte[] readData = new byte[largo];
    byte[] oldReadData = new byte[largo];
    HidDevice dev;

    int string_E, string_A,string_D,string_G,string_B,string_e;
    int notaString_E = 40;
    int notaString_A = 45;
    int notaString_D = 50;
    int notaString_G = 55;
    int notaString_B = 59;
    int notaString_e = 64;
    
    ControlCuerdas controlCuerdas;
    
    /*
    Cuerda cuerda_E ;
    Cuerda cuerda_A ;
    Cuerda cuerda_D ;
    Cuerda cuerda_G ;
    Cuerda cuerda_B ;
    Cuerda cuerda_e ;
    */
    
    boolean boton1=false;
    boolean boton2=false;
    boolean botonA=false;
    boolean botonB=false;
    
    public EntradaGuitarra(GuitarProperties gp, GeneralProperties prop) throws Exception{
        
        int vendorId = 7085;  // Reemplazar con el Vendor ID real
        int productId = 13360; // Reemplazar con el Product ID real


        // Crear instancia del servicio HID
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
        HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);

        System.out.println("Buscando dispositivos USB HID...");

        // Buscar el dispositivo específico
        dev = hidServices.getHidDevice(vendorId, productId, null);


        guitarProperties=gp;
        controlCuerdas = new ControlCuerdas(gp, prop);
    }

    
    public NotaMidi notaPulsada(int disp,int cuerda,int viejo,int nuevo){
        cuerda=cuerda-8;
        NotaMidi nota=null;
        boolean pulsado = Math.abs(nuevo-viejo)!=128;
        if (pulsado){
            int valorFuerza = nuevo>80?nuevo:80;
            if (cuerda==1){
                nota=new NotaMidi(disp,string_E+notaString_E, cuerda,guitarProperties.getString_strength_min(),guitarProperties.getString_strength_max() );
            }else if (cuerda==2){
                nota=new NotaMidi(disp,string_A+notaString_A, cuerda, guitarProperties.getString_strength_min(),guitarProperties.getString_strength_max());
            }else if (cuerda==3){
                nota=new NotaMidi(disp,string_D+notaString_D, cuerda, guitarProperties.getString_strength_min(),guitarProperties.getString_strength_max());
            }else if (cuerda==4){
                nota=new NotaMidi(disp,string_G+notaString_G, cuerda, guitarProperties.getString_strength_min(),guitarProperties.getString_strength_max());
            }else if (cuerda==5){
                nota=new NotaMidi(disp,string_B+notaString_B, cuerda, guitarProperties.getString_strength_min(),guitarProperties.getString_strength_max());
            }else if (cuerda==6){
                nota=new NotaMidi(disp,string_e+notaString_e, cuerda, guitarProperties.getString_strength_min(),guitarProperties.getString_strength_max());
            }
        }
        return nota;
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
        Map<Integer,DataCuerda> cuerdas = new HashMap<Integer, DataCuerda>();
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
            olddata[1]=data[1];
            if (data[1]==16){
                // enmudece todo
                setChanged();
                notifyObservers(new OrdenApagado());
            }else if (data[1]==1){
                // baja un semitono
                setChanged();
                notifyObservers(new OrdenBajoFuerza());

            } else if (data[1]==17){
                //sube un semitono 17
                setChanged();
                notifyObservers(new OrdenBotonMenos());

            } else if (data[1]==18){
                //vuelve tono al inicio 18
                setChanged();
                notifyObservers(new OrdenBotonMas());
                
            } else if (data[1]==3){
                //sube fuerzas topes 3
                setChanged();
                notifyObservers(new OrdenBotonMasMenos());

            }else if (data[1]==2){
                //baja fuerzas tope 2
                setChanged();
                notifyObservers(new OrdenSuboFuerza());
                
            } else if (data[1]==19){
                //vuelve fuerzas al origen
                setChanged();
                notifyObservers(new OrdenReseteoFuerza());

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
                setChanged();
                notifyObservers(new OrdenBoton1());
            }
            if (boton2){
                setChanged();
                notifyObservers(new OrdenBoton2());
                            setChanged();
            notifyObservers(new OrdenSwitchOnOffSlide());

            }
            if (botonA){
                setChanged();
                notifyObservers(new OrdenBotonA());
            }
            if (botonB){
                setChanged();
                notifyObservers(new OrdenBotonB());
            }

        }
        olddata[0]=data[0];

    }
    
    public void interpretoByte2(byte[] data, byte[] olddata){
        if (data[2]!=olddata[2]){
            if (data[2]==4){
                setChanged();
                notifyObservers(new OrdenToStrings());
            }
            if (data[2]==8){
                setChanged();
                notifyObservers(new OrdenMovePadOff());
            }
            if (data[2]==6){
                setChanged();
                notifyObservers(new OrdenToNeck());
            }
            
        }
        olddata[2]=data[2];
    }
    
    public void run() {
        while (true){
                        // Leer datos con un tiempo de espera de 1 segundo (1000 ms)
                        int bytesRead = dev.read(readData, 1000);

                        if (bytesRead > 0) {
                            // Comparar con la lectura anterior
                            if (!Arrays.equals(oldReadData, readData)) {
                                System.out.print("Nueva lectura de datos: ");
                                for (int i = 0; i < bytesRead; i++) {
                                    System.out.print( readData[i]+" ");
                                }
                                System.out.println();

                                // Actualizar la lectura anterior
                                System.arraycopy(readData, 0, oldReadData, 0, bytesRead);
                            }
                        } else {
                            System.out.println("No se recibieron datos...");
                        }
        }
    
    }
    


    public GuitarProperties getGuitarProperties() {
        return guitarProperties;
    }

    public void setGuitarProperties(GuitarProperties guitarProperties) {
        this.guitarProperties = guitarProperties;
    }
    
    
}
