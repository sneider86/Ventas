/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bodegas;
import com.frame.Conf.Conexion;
import com.frame.Conf.Utilidades;
import conf.Conf;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class ControllerBodegas {
    private Bodegas bodega;
    private Conexion con;
    /**
     * Constructor del Controller
     * @throws java.lang.Exception
     */
    public ControllerBodegas() throws Exception{
        try{
            this.initConexion();
        }catch(Exception err){
            throw new Exception();
        }
    }
    /**
     * Obtiene un objeto de tipo Bodegas
     * @return Bodegas
     */
    public Bodegas getBodegas(){
        return this.bodega;
    }
    /**
     * Establece un objeto de tipo Bodegas
     * @param bodega 
     */
    public void setBodega(Bodegas bodega){
        this.bodega=bodega;
    }
    /**
     * Inicia la conexion a la Base de Datos
     * @throws Exception 
     */
    private void initConexion() throws Exception{
        try{
            Properties pro = new Properties();
            InputStream in =  getClass().getResourceAsStream("/conf/PropConexion.properties");
            pro.load(in);
            String passkey=Conf.obtenerKeyPass();
            String iv=Conf.obtenerVectorIniciacion();
            
            String user=Utilidades.decrypt(passkey, iv, pro.getProperty("usuario"));
            String pass=Utilidades.decrypt(passkey, iv, pro.getProperty("clave"));
            String bd=Utilidades.decrypt(passkey, iv, pro.getProperty("basedatos"));
            String port=Utilidades.decrypt(passkey, iv, pro.getProperty("puerto"));
            String host=Utilidades.decrypt(passkey, iv, pro.getProperty("host"));
            String ssl=Utilidades.decrypt(passkey, iv, pro.getProperty("ssl"));
            
            this.con=new Conexion(user,pass);
            this.con.setBD(bd);
            this.con.setPuerto(port);
            this.con.setHost(host);
            this.con.setSSl(ssl);
            this.con.Conectar();
        }catch(Exception err){
            throw new Exception(err.getMessage());
        }
    }
    public void nuevaBodega()throws Exception{
        try{
            this.bodega.save();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,err.getMessage(),err.getCause().getMessage(),1);
        }
    }
}
