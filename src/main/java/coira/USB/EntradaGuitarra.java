/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.USB;


import coira.Midi.NotaMidi;
import coira.guitarra.Cuerda;
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
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class EntradaGuitarra extends Observable implements Runnable{
    
    GuitarProperties guitarProperties;
    int largo =32;
    byte[] readData = new byte[largo];
    byte[] oldReadData = new byte[largo];
    Device dev;

    int string_E, string_A,string_D,string_G,string_B,string_e;
    int notaString_E = 40;
    int notaString_A = 45;
    int notaString_D = 50;
    int notaString_G = 55;
    int notaString_B = 59;
    int notaString_e = 64;
    
    Cuerda cuerda_E ;
    Cuerda cuerda_A ;
    Cuerda cuerda_D ;
    Cuerda cuerda_G ;
    Cuerda cuerda_B ;
    Cuerda cuerda_e ;
    
    boolean boton1=false;
    boolean boton2=false;
    boolean botonA=false;
    boolean botonB=false;
    
    public EntradaGuitarra(GuitarProperties gp, GeneralProperties prop) throws USBException{
        dev = USB.getDevice((short)7085, (short)13360);
        dev.open(1, 0, -1);

        guitarProperties=gp;
        cuerda_E = new Cuerda(guitarProperties.getString_initial_value_lowE(), guitarProperties.getString_initial_channel_lowE(),prop.getPropertyAsInt("midi.port.String.lowE", guitarProperties.getString_initial_channel_lowE()),guitarProperties.getString_cc_selection_value_lowE(),guitarProperties.getString_key_selection_value_lowE());
        cuerda_A = new Cuerda(guitarProperties.getString_initial_value_A(), guitarProperties.getString_initial_channel_A(),prop.getPropertyAsInt("midi.port.String.A", guitarProperties.getString_initial_channel_A()),guitarProperties.getString_cc_selection_value_A(),guitarProperties.getString_key_selection_value_A());
        cuerda_D = new Cuerda(guitarProperties.getString_initial_value_D(), guitarProperties.getString_initial_channel_D(),prop.getPropertyAsInt("midi.port.String.D", guitarProperties.getString_initial_channel_D()),guitarProperties.getString_cc_selection_value_D(),guitarProperties.getString_key_selection_value_D());
        cuerda_G = new Cuerda(guitarProperties.getString_initial_value_G(), guitarProperties.getString_initial_channel_G(),prop.getPropertyAsInt("midi.port.String.G", guitarProperties.getString_initial_channel_G()),guitarProperties.getString_cc_selection_value_G(),guitarProperties.getString_key_selection_value_G());
        cuerda_B = new Cuerda(guitarProperties.getString_initial_value_B(), guitarProperties.getString_initial_channel_B(),prop.getPropertyAsInt("midi.port.String.B", guitarProperties.getString_initial_channel_B()),guitarProperties.getString_cc_selection_value_B(),guitarProperties.getString_key_selection_value_B());
        cuerda_e = new Cuerda(guitarProperties.getString_initial_value_highE(), guitarProperties.getString_initial_channel_highE(),prop.getPropertyAsInt("midi.port.String.highE", guitarProperties.getString_initial_channel_highE()),guitarProperties.getString_cc_selection_value_highE(),guitarProperties.getString_key_selection_value_highE());
        cuerda_E.setGuitarProperties(guitarProperties);
        cuerda_A.setGuitarProperties(guitarProperties);
        cuerda_D.setGuitarProperties(guitarProperties);
        cuerda_G.setGuitarProperties(guitarProperties);
        cuerda_B.setGuitarProperties(guitarProperties);
        cuerda_e.setGuitarProperties(guitarProperties);
        addObserver(cuerda_E);
        addObserver(cuerda_A);
        addObserver(cuerda_D);
        addObserver(cuerda_G);
        addObserver(cuerda_B);
        addObserver(cuerda_e);
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
        
      /*
        for (int i=9;i<15;i++){

            if (olddata[i]!=data[i]){
                NotaMidi nota = notaPulsada(i, olddata[i], data[i]);
                if (nota!=null){
                    setChanged();
                    notifyObservers(nota);
                }

            }
            
            olddata[i]=data[i];
            
        }
        */
        
        cuerda_E.interpretoEvento(string_E, data[9]);
        cuerda_A.interpretoEvento(string_A, data[10]);
        cuerda_D.interpretoEvento(string_D, data[11]);
        cuerda_G.interpretoEvento(string_G, data[12]);
        cuerda_B.interpretoEvento(string_B, data[13]);
        cuerda_e.interpretoEvento(string_e, data[14]);

        
        interpretoByte1(data,olddata);
        interpretoByte0(data,olddata);
        interpretoByte2(data,olddata);
        
        /*
        if (data[15]!=olddata[15]){
            olddata[15]=data[15];
            setChanged();
            notifyObservers(new OrdenApagado());
        }
        */
/*
        for (int i = 15; i < 18; i++) {
            if (data[i]!=olddata[i]){
                System.out.println("En la posicion "+i+" antes = "+olddata[i]+" ahora = "+data[i]);
                System.out.println("Detener");
                olddata[i]=data[i];
            }
            
        }
  */      
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
            try {
                dev.readInterrupt(0x81, readData, readData.length, 2000, false);
                detectoCambio(readData, oldReadData);
            } catch (USBException ex) {
                Logger.getLogger(EntradaGuitarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        
        cuerda_E.addObserver(o);
        cuerda_A.addObserver(o);
        cuerda_D.addObserver(o);
        cuerda_G.addObserver(o);
        cuerda_B.addObserver(o);
        cuerda_e.addObserver(o);
        
    }

    public GuitarProperties getGuitarProperties() {
        return guitarProperties;
    }

    public void setGuitarProperties(GuitarProperties guitarProperties) {
        this.guitarProperties = guitarProperties;
        cuerda_E.setGuitarProperties(guitarProperties);
        cuerda_E.setPrimerNota(guitarProperties.getString_initial_value_lowE());
        cuerda_A.setGuitarProperties(guitarProperties);
        cuerda_A.setPrimerNota(guitarProperties.getString_initial_value_A());
        cuerda_D.setGuitarProperties(guitarProperties);
        cuerda_D.setPrimerNota(guitarProperties.getString_initial_value_D());
        cuerda_G.setGuitarProperties(guitarProperties);
        cuerda_G.setPrimerNota(guitarProperties.getString_initial_value_G());
        cuerda_B.setGuitarProperties(guitarProperties);
        cuerda_B.setPrimerNota(guitarProperties.getString_initial_value_B());
        cuerda_e.setGuitarProperties(guitarProperties);
        cuerda_e.setPrimerNota(guitarProperties.getString_initial_value_highE());
    }
    
    
}
