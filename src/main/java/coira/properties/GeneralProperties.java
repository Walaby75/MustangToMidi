/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.accessibility.Accessible;

/**
 *
 * @author Administrador
 */
public class GeneralProperties {
    private List<String> configuraciones=new LinkedList<String>();
    private boolean cargarCfgs=false;
    private boolean guardarCfgs=false;
    Properties prop = new Properties();

    public List<String> getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(List<String> configuraciones) {
        this.configuraciones = configuraciones;
    }

    public boolean isCargarCfgs() {
        return cargarCfgs;
    }

    public void setCargarCfgs(boolean cargarCfgs) {
        this.cargarCfgs = cargarCfgs;
    }

    public boolean isGuardarCfgs() {
        return guardarCfgs;
    }

    public void setGuardarCfgs(boolean guardarCfgs) {
        this.guardarCfgs = guardarCfgs;
    }

    public GeneralProperties(String archivo) throws IOException {
        File f = new File("");
        prop.load(new FileInputStream(f.getAbsoluteFile()+archivo));
        guardarCfgs = !prop.getProperty("general.btns.save", "n").toLowerCase().contains("n");
        cargarCfgs = !prop.getProperty("general.btns.load", "n").toLowerCase().contains("n");
        for (int i=1;i<=Integer.parseInt(prop.getProperty("driver.quantity", "1"));i++){
            configuraciones.add(prop.getProperty("driver.model."+i, "realGuitar"));
            
        }
        
    }
    
    
    public int getPropertyAsInt(String property){
        return Integer.parseInt(prop.getProperty(property));
    }
    public int getPropertyAsInt(String property, int defValue){
        return Integer.parseInt(prop.getProperty(property,""+defValue));
    }
    
    public String getPropertyAsString(String property){
        return prop.getProperty(property);
    }
    public String getPropertyAsString(String property, String defValue){
        return prop.getProperty(property,defValue);
    }

    
    
}
