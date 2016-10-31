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
public class Terceros_tipo_tercero {
    private int TER_ID;
    private int TITER_ID;
    private String ESTADO;
    private String USUARIO;
    private String CLAVE;
    private int CLAVEINICIAL;
    private int PER_ID;
    private Conexion con;
    public Terceros_tipo_tercero(Conexion con){
        this.con=con;
        this.CLAVEINICIAL=1;
        this.ESTADO="1";
        this.USUARIO="";
        this.CLAVE="";
        this.PER_ID=0;
    }
    public int getTER_ID(){
        return this.TER_ID;
    }
    public void setTER_ID(int TER_ID){
        this.TER_ID=TER_ID;
    }
    public int getTITER_ID(){
        return this.TITER_ID;
    }
    public void setTITER_ID(int TITER_ID){
        this.TITER_ID=TITER_ID;
    }
    public String getESTADO(){
        return this.ESTADO;
    }
    public void setESTADO(String ESTADO){
        this.ESTADO=ESTADO;
    }
    public String getUSUARIO(){
        return this.USUARIO;
    }
    public void setUSUARIO(String USUARIO){
        this.USUARIO=USUARIO;
    }
    public String getCLAVE(){
        return this.CLAVE;
    }
    public void setCLAVE(String CLAVE){
        this.CLAVE=CLAVE;
    }
    public int getCLAVEINICIAL(){
        return this.CLAVEINICIAL;
    }
    public void setCLAVEINICIAL(int CLAVEINICIAL){
        this.CLAVEINICIAL=CLAVEINICIAL;
    }
    public Conexion getConexion(){
        return this.con;
    }
    public void setConexion(Conexion con){
        this.con=con;
    }
    public int getPER_ID(){
        return this.PER_ID;
    }
    public void setPER_ID(int PER_ID){
        this.PER_ID=PER_ID;
    }
    public boolean isExist() throws Exception{
        try{
            String sql="SELECT ESTADO FROM ven_tercero_tipo_tercero " +
                "WHERE TER_ID=? AND TITER_ID=?";
            this.con.prepararConsulta(sql);
            ParametrosQuery[] param=new ParametrosQuery[2];
            param[0]=new ParametrosQuery(1, this.TER_ID);
            param[1]=new ParametrosQuery(1, this.TITER_ID);
            ResultSet rs=this.con.consultaSeleccionParametros(param);
            return rs.next();
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Validando si existe"));
        }
    }
    private void nuevo() throws Exception{
        try{
            String sql="INSERT INTO ven_tercero_tipo_tercero(TER_ID,TITER_ID,ESTADO,USUARIO,CLAVE,CLAVEINICIAL) " +
                    "VALUES(?,?,?,?,MD5(?),?)";
            this.con.prepararConsulta(sql);
            ParametrosQuery[] param=new ParametrosQuery[6];
            param[0]=new ParametrosQuery(1, this.TER_ID);
            param[1]=new ParametrosQuery(1, this.TITER_ID);
            param[2]=new ParametrosQuery(2, this.ESTADO);
            param[3]=new ParametrosQuery(2, this.USUARIO);
            param[4]=new ParametrosQuery(2, this.CLAVE);
            param[5]=new ParametrosQuery(2, this.CLAVEINICIAL);
            //this.con.consultaSeleccionParametros(param);
            this.con.consultAccion(param);
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Insertando Nuevo"));
        }
    }
    public void save() throws Exception{
        try{
            if(this.isExist()){
        
            }else{
                this.nuevo();
            }
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable(err.getCause().getMessage()));
        }
        
    }
    
    
}
