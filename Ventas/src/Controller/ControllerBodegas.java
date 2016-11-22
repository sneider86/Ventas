/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bodegas;
import com.frame.Conf.Conexion;
import com.frame.Conf.ParametrosQuery;
import com.frame.Conf.Utilidades;
import conf.Conf;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

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
            this.bodega=new Bodegas(this.con);
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
    /**
     * Agrega una nueva Bodega
     * @throws Exception 
     */
    public void nuevaBodega()throws Exception{
        try{
            this.bodega.nuevo();
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable(err.getCause()));
        }
    }
    /**
     * Cargar los datos para la Grilla
     * @return ArrayList
     * @throws Exception 
     */
    public ArrayList<Object> loadDataGrid() throws Exception{
        ArrayList<Object> list=new ArrayList<>();
        try{
            String sql="SELECT BOD_ID,BOD_NOMBRE,BOD_ESTADO FROM ven_bodega";
            this.con.prepararConsulta(sql);
            ParametrosQuery param[]=new ParametrosQuery[0];
            ResultSet rs=this.con.consultaSeleccionParametros(param);
            while(rs.next()){
                Model.Bodegas obj=new Bodegas(this.con);
                obj.setBOD_ID(rs.getInt("BOD_ID"));
                obj.setBOD_NOMBRE(rs.getString("BOD_NOMBRE"));
                obj.setBOD_ESTADO(rs.getString("BOD_ESTADO"));
                list.add(obj);
            }
            this.con.cerrarResultyPrepared();
            this.con.cerrarConexion();
            return list;
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Problemas al cargar grilla"));
        }    
    }
    /**
     * 
     * @param id
     * @return Model.Bodegas
     * @throws Exception 
     */
    public Model.Bodegas cargarBodegaId(int id) throws Exception{
        try{
            Model.Bodegas bod=new Model.Bodegas(this.con);
            bod.setBOD_ID(id);
            if(bod.cargar()){
                this.con.cerrarResultyPrepared();
                this.con.cerrarConexion();
                return bod;
            }else{
                this.con.cerrarResultyPrepared();
                this.con.cerrarConexion();
                return null;
            }
        }catch(Exception err){
            String error=err.getMessage();
            String causa=err.getCause().getMessage();
            this.con.cerrarResultyPrepared();
            this.con.cerrarConexion();
            throw new Exception(error,new Throwable(causa));
        }
    }
    /**
     * 
     * @param id de la bodega
     * @param nombre de la bodega
     * @param estado de la bodega
     * @throws Exception 
     */
    public void actualizarBodega(int id,String nombre,String estado) throws Exception{
        try{
            Model.Bodegas bod=new Model.Bodegas(this.con);
            bod.setBOD_ID(id);
            bod.setBOD_NOMBRE(nombre);
            bod.setBOD_ESTADO(estado);
            bod.actualizar();
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable(err.getCause().getMessage()));
        }
    }
}
