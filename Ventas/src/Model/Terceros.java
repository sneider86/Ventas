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
 * Este objeto hace referecia a la tabla de terceros
 * @author erick
 */
public class Terceros {
    private int TERID;
    private String TERDOCUMENTO;
    private String TERTIPODOCUMENTO;
    private String NOMBRECOMPLETO;
    private String TERDIRECCION1;
    private String TERDIRECCION2;
    private String TERTELEFONO1;
    private String TERTELEFONO2;
    private String TEREMAIL;
    private String TERFECHANACIMIENTO;
    private final Conexion con;
    public Terceros(Conexion con){
        this.con=con;
        this.TERID = 0;
        this.TERDOCUMENTO = "";
        this.TERTIPODOCUMENTO = "";
        this.NOMBRECOMPLETO = "";
        this.TERDIRECCION1 = "";
        this.TERDIRECCION2 = "";
        this.TERTELEFONO1 = "";
        this.TERTELEFONO2 = "";
        this.TEREMAIL = "";
        this.TERFECHANACIMIENTO = "";
    }
    public int getTERID(){
        return this.TERID;
    }
    public void setTERID(int TERID){
        this.TERID=TERID;
    }
    public String getTERDOCUMENTO(){
        return this.TERDOCUMENTO;
    }
    public void setTERDOCUMENTO(String TERDOCUMENTO){
        this.TERDOCUMENTO=TERDOCUMENTO;
    }
    public String getTERTIPODOCUMENTO(){
        return this.TERTIPODOCUMENTO;
    }
    public void setTERTIPODOCUMENTO(String TERTIPODOCUMENTO){
        this.TERTIPODOCUMENTO=TERTIPODOCUMENTO;
    }
    public String getNOMBRECOMPLETO(){
        return this.NOMBRECOMPLETO;
    }
    public void setNOMBRECOMPLETO(String NOMBRECOMPLETO){
        this.NOMBRECOMPLETO=NOMBRECOMPLETO;
    }
    public String getTERDIRECCION1(){
        return this.TERDIRECCION1;
    }
    public void setTERDIRECCION1(String TERDIRECCION1){
        this.TERDIRECCION1=TERDIRECCION1;
    }
    public String getTERDIRECCION2(){
        return this.TERDIRECCION2;
    }
    public void setTERDIRECCION2(String TERDIRECCION2){
        this.TERDIRECCION2=TERDIRECCION2;
    }
    public String getTERTELEFONO1(){
        return this.TERTELEFONO1;
    }
    public void setTERTELEFONO1(String TERTELEFONO1){
        this.TERTELEFONO1=TERTELEFONO1;
    }
    public String getTERTELEFONO2(){
        return this.TERTELEFONO2;
    }
    public void setTERTELEFONO2(String TERTELEFONO2){
        this.TERTELEFONO2=TERTELEFONO2;
    }
    public String getTEREMAIL(){
        return this.TEREMAIL;
    }
    public void setTEREMAIL(String TEREMAIL){
        this.TEREMAIL=TEREMAIL;
    }
    public String getTERFECHANACIMIENTO(){
        return this.TERFECHANACIMIENTO;
    }
    public void setTERFECHANACIMIENTO(String TERFECHANACIMIENTO){
        this.TERFECHANACIMIENTO=TERFECHANACIMIENTO;
    }
    public boolean login(String usuario,String clave) throws Exception{
        try{
            String sql="SELECT " +
                            "t.TER_ID," +
                            "TER_DOCUMENTO," +
                            "TER_TIPODOCUMENTO," +
                            "TER_NOMBRECOMPLETO," +
                            "TER_DIRECCION1," +
                            "TER_DIRECCION2," +
                            "TER_TELEFONO1," +
                            "TER_TELEFONO2," +
                            "TER_EMAIL," +
                            "TER_FECHANACIMIENTO " +
                        "FROM ven_tercero_tipo_tercero tt " +
                        "INNER JOIN ven_terceros t ON(tt.TER_ID=t.TER_ID) " +
                        "WHERE USUARIO=? " +
                        "AND CLAVE=MD5(?)";
            this.con.prepararConsulta(sql);
            ParametrosQuery[] param=new ParametrosQuery[2];
            param[0]=new ParametrosQuery(2,usuario);
            param[1]=new ParametrosQuery(2,clave);
            ResultSet rs=this.con.consultaSeleccionParametros(param);
            if(rs.next()){
                this.TERID = rs.getInt("TER_ID");
                this.TERDOCUMENTO = rs.getString("TER_DOCUMENTO");
                this.TERTIPODOCUMENTO = rs.getString("TER_TIPODOCUMENTO");
                this.NOMBRECOMPLETO = rs.getString("TER_NOMBRECOMPLETO");
                this.TERDIRECCION1 = rs.getString("TER_DIRECCION1");
                this.TERDIRECCION2 = rs.getString("TER_DIRECCION2");
                this.TERTELEFONO1 = rs.getString("TER_TELEFONO1");
                this.TERTELEFONO2 = rs.getString("TER_TELEFONO2");
                this.TEREMAIL = rs.getString("TER_EMAIL");
                this.TERFECHANACIMIENTO = rs.getString("TER_FECHANACIMIENTO");
                return true;
            }
            return false;
        }catch(Exception err){
            throw new Exception(err.getMessage(),new Throwable("Error de Base de Datos"));
        }
    }      
            
}
