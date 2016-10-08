/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import com.frame.Conf.Conexion;
import com.frame.Conf.Utilidades;
import conf.Conf;
import java.io.InputStream;
import java.util.Properties;
import com.frame.Conf.ComboBoxCustom;
import javax.swing.JOptionPane;
/**
 *
 * @author erick
 */
public class ControllerTerceros {
    private Conexion con;
    public ControllerTerceros(){
        try{
            this.initConexion();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,err.getMessage(),"Error",2);
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
    public void llenarComboTipoDocumento(ComboBoxCustom combo){
        
        combo.addItem("key1","Value1");
        combo.addItem("key2","Value2");
        combo.addItem("key3","Value3");
    }
}
