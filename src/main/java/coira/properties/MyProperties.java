/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.properties;

import java.util.Properties;

/**
 *
 * @author Administrador
 */
public class MyProperties extends Properties{
    
    public String getProperty(String key,String defaultValue){
        String retorno = defaultValue;
        try {
            retorno = getProperty(key);
            if (retorno == null){
                System.out.println("No se encuentra clave "+key);
                System.out.println("Se usa valor defecto "+key);
                retorno=defaultValue;
            }
        } catch (Exception e) {
        }
        return retorno;
    }
    
}
