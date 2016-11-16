/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.frame.Conf.Conexion;
import com.frame.Conf.ParametrosQuery;
import java.sql.ResultSet;

/**
 *
 * @author erick
 */
public class Bodegas {
    private Conexion con;
    private int BOD_ID;
    private String BOD_NOMBRE;
    private String BOD_ESTADO;
    /**
     * Constructor de la clase Bodegas
     * @param con Conexion
     */
    public Bodegas(Conexion con){
        this.BOD_ID=0;
        this.BOD_NOMBRE="";
        this.BOD_ESTADO="1";
        this.con=con;
    }
    /**
     * Retorna el id de la Bodega.
     * @return Entero
     */
    public int getBOD_ID(){
        return this.BOD_ID;
    }
    /**
     * Establece el valor del id de la Bodega.
     * @param BOD_ID Entero
     */
    public void setBOD_ID(int BOD_ID){
        this.BOD_ID=BOD_ID;
    }
    /**
     * Obtiene el nombre de la Bodega.
     * @return String
     */
    public String getBOD_NOMBRE(){
        return this.BOD_NOMBRE;
    }
    /**
     * Establece el nombre de la Bodega.
     * @param BOD_NOMBRE String
     */
    public void setBOD_NOMBRE(String BOD_NOMBRE){
        this.BOD_NOMBRE=BOD_NOMBRE;
    }
    /**
     * Obtiene el estado de la Bodega.
     * @return String
     */
    public String getBOD_ESTADO(){
        return this.BOD_ESTADO;
    }
    /**
     * Establece el estado de la Bodega.
     * @param BOD_ESTADO String
     */
    public void setBOD_ESTADO(String BOD_ESTADO){
        this.BOD_ESTADO=BOD_ESTADO;
    }
    /**
     * Obtiene el objeto Conexion
     * @return Conexion
     */
    public Conexion getConexion(){
        return this.con;
    }
    /**
     * Establece el objeto Conexion.
     * @param con Conexion
     */
    public void setConexion(Conexion con){
        this.con=con;
    }
    /**
     * Verifica si el id de bodega o nombre existen.
     * @return Boolean
     */
    private boolean isExist() throws Exception{
        try{
            String sql="SELECT BOD_ID "
                    + "FROM ven_bodega "
                    + "WHERE "
                    + "BOD_ID = ?";
            this.con.prepararConsulta(sql);
            ParametrosQuery[] param=new ParametrosQuery[1];
            param[0]=new ParametrosQuery(1,this.BOD_ID);
            ResultSet rs=this.con.consultaSeleccionParametros(param);
            return rs.next();
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Verificando existencia"));
        }
    }
    /**
     * Graba datos de una bodega nuevo o existente
     * @throws Exception 
     */
    public void save() throws Exception{
        try{
            if(this.BOD_ID>0){
                if(this.isExist()){
                    
                }else{
                    this.inset();
                }
            }else{
                this.inset();
            }
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Grabando"));
        }
    }
    /**
     * Nuevo registro de Bodega.
     * @throws Exception 
     */
    private void inset() throws Exception{
        try{
            String sql="INSERT INTO ven_bodega(BOD_NOMBRE,BOD_ESTADO) "
                    + "VALUES(?,?)";
            ParametrosQuery[] param=new ParametrosQuery[2];
            param[0]=new ParametrosQuery(2,this.BOD_NOMBRE);
            param[1]=new ParametrosQuery(2,this.BOD_ESTADO);
            this.con.prepararConsulta(sql);
            this.con.consultAccion(param);
            ResultSet rs=this.con.getPreparedStatement().getGeneratedKeys();
            rs.next();
            this.BOD_ID=rs.getInt(1);
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Nuevo"));
        }
    }
    /**
     * Graba datos de una bodega nueva
     * @throws Exception 
     */
    public void nuevo() throws Exception{
        try{
            if(this.BOD_ID>0){
                if(this.isExist()){
                    throw new Exception("No se pudo grabar la bodega porque ya existe.",new Throwable("Grabando"));
                }else{
                    this.inset();
                }
            }else{
                this.inset();
            }
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Grabando"));
        }
    }
    /**
     * Obtiene los datos de una Bodega por el id
     * @return boolean
     * @throws Exception 
     */
    public boolean cargar() throws Exception{
        try{
            String sql="SELECT BOD_ID,BOD_NOMBRE,BOD_ESTADO "
                    + "FROM ven_bodega "
                    + "WHERE "
                    + "BOD_ID = ?";
            this.con.prepararConsulta(sql);
            ParametrosQuery[] param=new ParametrosQuery[1];
            param[0]=new ParametrosQuery(1,this.BOD_ID);
            ResultSet rs=this.con.consultaSeleccionParametros(param);
            if(rs.next()){
                this.BOD_NOMBRE=rs.getString("BOD_NOMBRE");
                this.BOD_ESTADO=rs.getString("BOD_ESTADO");
                return true;
            }else{
                return false;
            }
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Cargando Datos"));
        }
    }
    public void actualizar() throws Exception{
        try{
            if(this.BOD_ID>0){
                if(this.isExist()){
                    String sql="UPDATE ven_bodega SET BOD_NOMBRE=?,BOD_ESTADO=? WHERE BOD_ID=?";
                    this.con.prepararConsulta(sql);
                    ParametrosQuery param[]=new ParametrosQuery[3];
                    param[0]=new ParametrosQuery(2,this.BOD_NOMBRE);
                    param[1]=new ParametrosQuery(2,this.BOD_ESTADO);
                    param[2]=new ParametrosQuery(1,this.BOD_ID);
                    this.con.consultAccion(param);
                    
                }else{
                    throw new Exception("No se pudo actualizar porque no existe el codigo de bodega",new Throwable("Actualizando"));
                }
            }else{
                throw new Exception("Codigo de Bodega incorrecto",new Throwable("Actualizando"));
            }
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Actualizando"));
        }
    }
    
}
