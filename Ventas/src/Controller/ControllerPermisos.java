/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.frame.Conf.Conexion;
import com.frame.Conf.ParametrosQuery;
import com.frame.Conf.Utilidades;
import conf.Conf;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *
 * @author erick
 */
public class ControllerPermisos {
    private int PER_ID;
    private int MOD_ID;
    private boolean VER;
    private boolean GRABAR;
    private boolean ACTUALIZAR;
    private Conexion con;
    /**
     * Constructor
     * @param con Conexion
     * @throws Exception 
     */
    public ControllerPermisos() throws Exception{
        try{
            this.initConexion();
            this.PER_ID=0;
            this.MOD_ID=0;
            this.VER=false;
            this.GRABAR=false;
            this.ACTUALIZAR=false;
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Error al conectarce"));
        }
    }
    /**
     * Constructor
     * @param PER_ID Entero
     * @param MOD_ID Entero
     * @throws Exception 
     */
    public ControllerPermisos(int PER_ID,int MOD_ID) throws Exception{
        try{
            this.initConexion();
            this.PER_ID=PER_ID;
            this.MOD_ID=MOD_ID;
            this.VER=false;
            this.GRABAR=false;
            this.ACTUALIZAR=false;
           this.obtenerPermisos();
           this.con.cerrarResultyPrepared();
           this.con.cerrarConexion();
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable(err.getCause().getMessage()));
        }
    }
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
    /**
     * Obtiene el id del perfil
     * @return Entero
     */
    public int getPER_ID(){
        return this.PER_ID;
    }
    /**
     * Se establece el id del perfil.
     * @param PER_ID Entero
     */
    public void setPER_ID(int PER_ID){
        this.PER_ID=PER_ID;
    }
    /**
     * Se obtiene el id del modulo
     * @return Entero
     */
    public int getMOD_ID(){
        return this.MOD_ID;
    }
    /**
     * Se establece el id del modulo.
     * @param MOD_ID Entero
     */
    public void setMOD_ID(int MOD_ID){
        this.MOD_ID=MOD_ID;
    }
    /**
     * Se obtiene la verificacion de que el perfil pueda ver un modulo.
     * @return boolean
     */
    public boolean getVER(){
        return this.VER;
    }
    /**
     * Se establece la verificación de que el perfil pueda ver un modulo
     * @param VER boolean
     */
    public void setVER(boolean VER){
        this.VER=VER;
    }
    /**
     * Se obtiene la verificación de que el perfil puede guardar en un modulo.
     * @return boolean
     */
    public boolean getGRABAR(){
        return this.GRABAR;
    }
    /**
     * Se establece la verificación de que el perfil puede guardar en un modulo.
     * @param GRABAR boolean
     */
    public void setGRABAR(boolean GRABAR){
        this.GRABAR=GRABAR;
    }
    /**
     * Se obtiene la verificación de que el perfil puede actualizar en un modulo.
     * @return boolean
     */
    public boolean getACTUALIZAR(){
        return this.ACTUALIZAR;
    }
    /**
     * Se establece la verificación de que el perfil puede actualizar en un modulo.
     * @param ACTUALIZAR boolean
     */
    public void setACTUALIZAR(boolean ACTUALIZAR){
        this.ACTUALIZAR=ACTUALIZAR;
    }
    /**
     * Obtiene los permisos de un perfil en un modulo
     * @throws Exception 
     */
    public void obtenerPermisos() throws Exception{
        try{
            String sql="SELECT VER,GRABAR,ACTUALIZAR " +
                "FROM ven_permiso " +
                "WHERE PER_ID=? AND MOD_ID=?";
            ParametrosQuery[] param=new ParametrosQuery[2];
            this.con.prepararConsulta(sql);
            param[0]=new ParametrosQuery(1,this.PER_ID);
            param[1]=new ParametrosQuery(1,this.MOD_ID);
            ResultSet rs=this.con.consultaSeleccionParametros(param);
            if(rs.next()){
                boolean v=false;
                boolean g=false;
                boolean a=false;
                if(rs.getInt("VER")>0){
                    v=true;
                }
                if(rs.getInt("GRABAR")>0){
                    g=true;
                }
                if(rs.getInt("ACTUALIZAR")>0){
                    a=true;
                }
                this.VER=v;
                this.GRABAR=g;
                this.ACTUALIZAR=a;
            }
            this.con.cerrarResultyPrepared();
            this.con.cerrarConexion();
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Error al obener permisos"));
        }
    }
    
}
