/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuraciones.salida;

/**
 *
 * @author Usuario
 */
public class DataCFGSalida {
    String puerto;
    Integer canal;

    public DataCFGSalida(String puerto, Integer canal) {
        this.puerto = puerto;
        this.canal = canal;
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
    
    
}
