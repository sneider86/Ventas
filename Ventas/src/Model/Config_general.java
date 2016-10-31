/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import com.frame.Conf.Conexion;
import com.frame.Conf.ParametrosQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.ResultKeyVal;
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
    /**
     * Este es el modelo para la tabla de configuración General.
     * @param con 
     */
    public Config_general(Conexion con){
        this.con=con;
    }
    /**
     * Retorna Obtiene el nombre del componente al que pertenece la configuración
     * @return Retorna un String
     */
    public String getCONF_COMPONENTE(){
        return this.CONF_COMPONENTE;
    }
    /**
     * Se establece el nombre del parametro al que pertenece la configuración.
     * @param CONF_COMPONENTE Recibe un String.
     */
    public void setCONF_COMPONENTE(String CONF_COMPONENTE){
        this.CONF_COMPONENTE=CONF_COMPONENTE;
    }
    /**
     * Obtiene el key de la configuración.
     * @return Retorna un String
     */
    public String getCONF_KEY(){
        return this.CONF_KEY;
    }
    /**
     * Establece el key de la configuración.
     * @param CONF_KEY Recibe un String.
     */
    public void seCONF_KEY(String CONF_KEY){
        this.CONF_KEY=CONF_KEY;
    }
    /**
     * Obtiene el valor de la configuración.
     * @return Retorna un String
     */
    public String getCONF_VALUE(){
        return this.CONF_VALUE;
    }
    /**
     * Establece el valor de la configuración.
     * @param CONF_VALUE Recibe un String
     */
    public void setCONF_VALUE(String CONF_VALUE){
        this.CONF_VALUE=CONF_VALUE;
    }
    /**
     * Obtiene el orden de la configuración.
     * @return Retorna un String.
     */
    public String getCONF_ORDEN(){
        return this.CONF_ORDEN;
    }
    /**
     * Establece el orden de la configuración.
     * @param CONF_ORDEN Recibe un String
     */
    public void setCONF_ORDEN(String CONF_ORDEN){
        this.CONF_ORDEN=CONF_ORDEN;
    }
    /**
     * 
     * @return Retorna la conexion.
     */
    public Conexion getConexion(){
        return this.con;
    }
    /**
     * Se establece la conexion.
     * @param con Recibe Conexion.
     */
    public void setConexion(Conexion con){
        this.con=con;
    }
    public ArrayList obtenerListadoConf() throws Exception{
        ArrayList lista=new ArrayList();
        try{
            String sql="SELECT CONF_KEY, CONF_VALUE FROM ven_config_general WHERE CONF_COMPONENTE = ? ORDER BY CONF_ORDEN ASC";
            this.con.prepararConsulta(sql);
            ParametrosQuery[] param=new ParametrosQuery[1];
            param[0]=new ParametrosQuery(2, this.CONF_COMPONENTE);
            ResultSet rs=this.con.consultaSeleccionParametros(param);
            while(rs.next()){
                ResultKeyVal keyval=new ResultKeyVal(rs.getString("CONF_KEY"), rs.getString("CONF_VALUE"));
                lista.add(keyval);
            }
            return lista;
        }catch(Exception err){
            throw new Exception(err.getMessage());
        }
    }
}
