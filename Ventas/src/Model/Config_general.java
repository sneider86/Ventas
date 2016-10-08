/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import com.frame.Conf.Conexion;
/**
 *
 * @author erick
 */
public class Config_general {
    private Conexion con;
    private String CONF_COMPONENTE;
    private String CONF_KEY;
    private String CONF_VALUE;
    private String CONF_ORDEN;
    public Config_general(Conexion con){
        this.con=con;
    }
    public String getCONF_COMPONENTE(){
        return this.CONF_COMPONENTE;
    }
    public void setCONF_COMPONENTE(String CONF_COMPONENTE){
        this.CONF_COMPONENTE=CONF_COMPONENTE;
    }
    public String getCONF_KEY(){
        return this.CONF_KEY;
    }
    public void seCONF_KEY(String CONF_KEY){
        this.CONF_KEY=CONF_KEY;
    }
    public String getCONF_VALUE(){
        return this.CONF_VALUE;
    }
    public void setCONF_VALUE(String CONF_VALUE){
        this.CONF_VALUE=CONF_VALUE;
    }
    
}
