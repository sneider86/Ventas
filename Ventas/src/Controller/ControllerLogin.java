/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Terceros;
import com.frame.Conf.Conexion;
import com.frame.Conf.Utilidades;
import conf.Conf;
import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author erick
 */
public class ControllerLogin {
    private Conexion con;
    public ControllerLogin() throws Exception{
        try{
            this.initConexion();
        }catch(Exception err){
            throw new Exception(err.getMessage());
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
    public Terceros login(String usuario,String clave) throws Exception{
        if(!"".equals(usuario.toUpperCase().trim())){
            if(!"".equals(clave.toUpperCase().trim())){
                try{
                    this.initConexion();
                    Terceros tercero=new Terceros(this.con);
                    if(tercero.login(usuario, clave)){
                        this.con.cerrarResultyPrepared();
                        this.con.cerrarConexion();
                        return tercero;
                    }else{
                        return null;
                    }
                }catch(Exception err){
                    throw new Exception(err.getMessage(),err.getCause());
                }
            }else{
                throw new Exception("Clave no puede estar vacia",new Throwable("Validación de dato"));
            }
        }else{
            throw new Exception("Usuario no puede estar vacio",new Throwable("Validación de dato"));
        }
    }
}
