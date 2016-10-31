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
import Model.Config_general;
import Model.ResultKeyVal;
import Model.Terceros;
import Model.Terceros_tipo_tercero;
import Views.ActualizarTercero;
import Views.NuevoTercero;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        try{
            Config_general config=new Config_general(this.con);
            config.setCONF_COMPONENTE("TIPO_DOCUMENTO");
            ArrayList lista=config.obtenerListadoConf();
            for (Object lista1 : lista) {
                ResultKeyVal item=(ResultKeyVal)lista1;
                combo.addItem(item.key,item.value);
            }
            this.con.cerrarResultyPrepared();
            this.con.cerrarConexion();
        }catch(Exception err){
        
        }
    }
    
    public void grabarTercero(NuevoTercero ventanatercero) throws Exception{
        if(!"".equals(ventanatercero.getDocumento().trim())){
            if(!"".equals(ventanatercero.getNombre().trim())){
                if(!"".equals(ventanatercero.getDireccion1().trim())){
                    if(!"".equals(ventanatercero.getTelefono1().trim())){
                        try{
                            this.con.getCon().setAutoCommit(false);
                            String tipdoc=ventanatercero.getTipoDocumento();
                            String documento=ventanatercero.getDocumento().trim().toUpperCase();
                            String nombre=ventanatercero.getNombre().trim().toUpperCase();
                            String dir1=ventanatercero.getDireccion1().trim().toUpperCase();
                            String dir2=ventanatercero.getDireccion2().trim().toUpperCase();
                            String tel1=ventanatercero.getTelefono1().trim().toUpperCase();
                            String tel2=ventanatercero.getTelefono2().trim().toUpperCase();
                            String email=ventanatercero.getEmail().trim();
                            String fechanac=ventanatercero.getFechaNacimiento();
                            Terceros tercero=new Terceros(this.con);
                            tercero.setTERTIPODOCUMENTO(tipdoc);
                            tercero.setTERDOCUMENTO(documento);
                            tercero.setNOMBRECOMPLETO(nombre);
                            tercero.setTERDIRECCION1(dir1);
                            tercero.setTERDIRECCION2(dir2);
                            tercero.setTERTELEFONO1(tel1);
                            tercero.setTERTELEFONO2(tel2);
                            tercero.setTEREMAIL(email);
                            Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechanac);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String parsedDate = formatter.format(initDate);
                            tercero.setTERFECHANACIMIENTO(parsedDate);
                            if(tercero.isExist()){
                                if(ventanatercero.getPermiso().getACTUALIZAR()){
                                    tercero.save();
                                }else{
                                    JOptionPane.showMessageDialog(null,"No tiene permisos para actualizar","Permiso",2);
                                }
                            }else{
                                tercero.save();
                                if(ventanatercero.getTipoUsuario()){
                                    Terceros_tipo_tercero tipo1=new Terceros_tipo_tercero(this.con);
                                    tipo1.setTER_ID(tercero.getTERID());
                                    tipo1.setTITER_ID(1);
                                    tipo1.save();
                                    this.con.getCon().commit();
                                    this.con.getCon().setAutoCommit(true);
                                    this.con.cerrarConexion();
                                }
                                JOptionPane.showMessageDialog(null,"Tercero grabado con exito","Exito",1);
                            }
                        }catch(Exception err){
                            try{
                                this.con.getCon().rollback();
                                this.con.cerrarConexion();
                                System.out.println("Rollback: "+err.getMessage());
                            }catch(Exception err2){
                                throw new Exception("No se pudo retroceder cambios",new Throwable("Rollback"));
                            }
                        }
                    }else{
                        throw new Exception("Digite el teléfono 1",new Throwable("Validación"));
                    }
                }else{
                    throw new Exception("Digite la dirección 1",new Throwable("Validación"));
                }
            }else{
                throw new Exception("Digite el nombre completo",new Throwable("Validación"));
            }
        }else{
            throw new Exception("Digite el numero de documento",new Throwable("Validación"));
        }        
    }
    public void ActualizarTercero(ActualizarTercero ventanatercero) throws Exception{
        if(!"".equals(ventanatercero.getDocumento().trim())){
            if(!"".equals(ventanatercero.getNombre().trim())){
                if(!"".equals(ventanatercero.getDireccion1().trim())){
                    if(!"".equals(ventanatercero.getTelefono1().trim())){
                        try{
                            this.con.getCon().setAutoCommit(false);
                            String tipdoc=ventanatercero.getTipoDocumento();
                            String documento=ventanatercero.getDocumento().trim().toUpperCase();
                            String nombre=ventanatercero.getNombre().trim().toUpperCase();
                            String dir1=ventanatercero.getDireccion1().trim().toUpperCase();
                            String dir2=ventanatercero.getDireccion2().trim().toUpperCase();
                            String tel1=ventanatercero.getTelefono1().trim().toUpperCase();
                            String tel2=ventanatercero.getTelefono2().trim().toUpperCase();
                            String email=ventanatercero.getEmail().trim();
                            String fechanac=ventanatercero.getFechaNacimiento();
                            Terceros tercero=new Terceros(this.con);
                            tercero.setTERTIPODOCUMENTO(tipdoc);
                            tercero.setTERDOCUMENTO(documento);
                            tercero.setNOMBRECOMPLETO(nombre);
                            tercero.setTERDIRECCION1(dir1);
                            tercero.setTERDIRECCION2(dir2);
                            tercero.setTERTELEFONO1(tel1);
                            tercero.setTERTELEFONO2(tel2);
                            tercero.setTEREMAIL(email);
                            Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechanac);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String parsedDate = formatter.format(initDate);
                            tercero.setTERFECHANACIMIENTO(parsedDate);
                            if(tercero.isExist()){
                                if(ventanatercero.getPermiso().getACTUALIZAR()){
                                    tercero.save();
                                }else{
                                    JOptionPane.showMessageDialog(null,"No tiene permisos para actualizar","Permiso",2);
                                }
                            }else{
                                tercero.save();
                                if(ventanatercero.getTipoUsuario()){
                                    Terceros_tipo_tercero tipo1=new Terceros_tipo_tercero(this.con);
                                    tipo1.setTER_ID(tercero.getTERID());
                                    tipo1.setTITER_ID(1);
                                    tipo1.save();
                                    this.con.getCon().commit();
                                    this.con.getCon().setAutoCommit(true);
                                    this.con.cerrarConexion();
                                }
                                JOptionPane.showMessageDialog(null,"Tercero grabado con exito","Exito",1);
                            }
                        }catch(Exception err){
                            try{
                                this.con.getCon().rollback();
                                this.con.cerrarConexion();
                                System.out.println("Rollback: "+err.getMessage());
                            }catch(Exception err2){
                                throw new Exception("No se pudo retroceder cambios",new Throwable("Rollback"));
                            }
                        }
                    }else{
                        throw new Exception("Digite el teléfono 1",new Throwable("Validación"));
                    }
                }else{
                    throw new Exception("Digite la dirección 1",new Throwable("Validación"));
                }
            }else{
                throw new Exception("Digite el nombre completo",new Throwable("Validación"));
            }
        }else{
            throw new Exception("Digite el numero de documento",new Throwable("Validación"));
        }        
    }

}
