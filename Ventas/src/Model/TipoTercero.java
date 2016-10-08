/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import com.frame.Conf.Conexion;
import java.util.ArrayList;
/**
 *
 * @author erick
 */
public class TipoTercero {
    private Conexion con;
    private int TITER_ID;
    private String TITER_NOMBRE;
    private int TITER_ESTADO;
    
    public TipoTercero(){
        this.TITER_ESTADO=1;
    }
    public int getTITER_ID(){
        return this.TITER_ID;
    }
    public void setTITER_ID(int TITER_ID){
        this.TITER_ID=TITER_ID;
    }
    public String getTITER_NOMBRE(){
        return this.TITER_NOMBRE;
    }
    public void setTITER_NOMBRE(String TITER_NOMBRE){
        this.TITER_NOMBRE=TITER_NOMBRE;
    }
    public int getTITER_ESTADO(){
        return this.TITER_ESTADO;
    }
    public void setTITER_ESTADO(int TITER_ESTADO){
        this.TITER_ESTADO=TITER_ESTADO;
    }
    public ArrayList obtenerTiposTerceros() throws Exception{
        try{
            ArrayList lista=new ArrayList();
            if(this.con!=null){
                
            }else{
                throw new Exception("No hay conexion con la Base de Datos");
            }
            return lista;
        }catch(Exception err){
            throw new Exception(err.getMessage());
        }
    }
}
