/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.ControllerBodegas;
import Controller.ControllerPermisos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class ActualizarBodega extends javax.swing.JFrame {
    private ControllerPermisos permisos;
    private int id;
    /**
     * Creates new form NuevaBodega
     */
    public ActualizarBodega() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public ActualizarBodega(ControllerPermisos permisos,int id) {
        this.id=id;
        initComponents();
        this.initCustom();
        this.permisos=permisos;
        this.setLocationRelativeTo(null);
    }
    private void initCustom(){
        this.cmbestado.addItem("1","Activo");
        this.cmbestado.addItem("0","Inactivo");
        try{
            ControllerBodegas controller=new ControllerBodegas();
            Model.Bodegas bod=controller.cargarBodegaId(this.id);
            if(bod!=null){
                this.txtnombre.setText(bod.getBOD_NOMBRE());
                this.cmbestado.setKey(bod.getBOD_ESTADO());
            }else{
                JOptionPane.showMessageDialog(null,"No se pudieron cargar los dato a editar","Error",0);
            }
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,err.getMessage(),err.getCause().getMessage(),0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtnombre = new com.frame.Conf.TextFieldCustom();
        btngrabar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbestado = new com.frame.Conf.ComboBoxCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Bodega");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Nombre");

        txtnombre.setPlaceholder("Digite el Nombre");

        btngrabar.setText("Actualizar");
        btngrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngrabarActionPerformed(evt);
            }
        });

        jLabel2.setText("Estado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btngrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btngrabar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btngrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngrabarActionPerformed
        if(this.permisos.getACTUALIZAR()){
            if(!this.txtnombre.getText().trim().equals("")){
                try {
                    ControllerBodegas controller=new ControllerBodegas();
                    String nombre=this.txtnombre.getText().trim().toUpperCase();
                    String estado=this.cmbestado.getKey();
                    controller.actualizarBodega(id,nombre,estado);
                    JOptionPane.showMessageDialog(null,"Bodega Grabada con exito","Información",1);
                    Views.Bodegas bod=new Bodegas(this.permisos);
                    bod.setVisible(true);
                    this.setVisible(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),ex.getCause().getMessage(),0);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Debe ingresar el nombre de la bodega","Advertencia",2);
            }
        }else{
            JOptionPane.showMessageDialog(null,"No tiene permisos para actualizar la bodega","Permiso",2);
        }
    }//GEN-LAST:event_btngrabarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Views.Bodegas bod=new Bodegas(this.permisos);
        bod.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ActualizarBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarBodega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btngrabar;
    private com.frame.Conf.ComboBoxCustom cmbestado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.frame.Conf.TextFieldCustom txtnombre;
    // End of variables declaration//GEN-END:variables
}
