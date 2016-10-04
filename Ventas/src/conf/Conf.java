/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

/**
 *
 * @author erick
 */
import java.util.Properties;
public class Conf {
    
    /**
     * Función de tipo String que retorna el Vector de Iniciacion de la app
     * @return el valor del IV
     */
    public static String obtenerVectorIniciacion(){
        return "0123456789ABCDEF";
    }
    
    /**
     * Función de tipo String que retorna el KeyPass para encryptar, de la app
     * @return el valor dek KeyPass
     */
    public static String obtenerKeyPass(){
        return "0123456789ABCDEF";
    }
}
