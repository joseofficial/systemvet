/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Santa206
 */
public class usuarioVista extends javax.swing.JFrame {

    public boolean mostrar= true;
    
    public usuarioVista() {
        initComponents();
        
        txtcontrasena.setVisible(true);
        txtmostrar.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtbuscarusuario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablausuario = new javax.swing.JTable();
        btnaceptar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        cmbtipo = new javax.swing.JComboBox<>();
        optactivo = new javax.swing.JRadioButton();
        optinactivo = new javax.swing.JRadioButton();
        cmbpersonal = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtcontrasena = new javax.swing.JPasswordField();
        txtmostrar = new javax.swing.JTextField();
        btnmostrar = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BUSCAR");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 60, 20));
        jPanel2.add(txtbuscarusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 510, -1));

        tablausuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "NOMBRE", "CLAVE", "TIPO", "ESTADO", "COD ", "PERSONAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablausuario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablausuario);
        if (tablausuario.getColumnModel().getColumnCount() > 0) {
            tablausuario.getColumnModel().getColumn(0).setResizable(false);
            tablausuario.getColumnModel().getColumn(1).setResizable(false);
            tablausuario.getColumnModel().getColumn(2).setResizable(false);
            tablausuario.getColumnModel().getColumn(3).setResizable(false);
            tablausuario.getColumnModel().getColumn(4).setResizable(false);
            tablausuario.getColumnModel().getColumn(5).setResizable(false);
            tablausuario.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 580, 220));

        btnaceptar.setText("ACEPTAR");
        jPanel2.add(btnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, 30));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );

        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USUARIO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 110, 20));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CONTRASEÑA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 130, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TIPO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 100, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ESTADO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 130, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PERSONAL");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 160, -1));
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 200, -1));

        cmbtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE UN TIPO--", "ADMINISTRADOR", "INVITADO" }));
        jPanel1.add(cmbtipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 200, -1));

        buttonGroup1.add(optactivo);
        optactivo.setText("ACTIVO");
        optactivo.setBorder(null);
        jPanel1.add(optactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, -1));

        buttonGroup1.add(optinactivo);
        optinactivo.setText("INACTIVO");
        optinactivo.setBorder(null);
        jPanel1.add(optinactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, -1));

        cmbpersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SELECCIONE UN PERSONAL --" }));
        jPanel1.add(cmbpersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 190, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("USUARIO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N
        btnnuevo.setToolTipText("NUEVO");
        btnnuevo.setContentAreaFilled(false);
        jPanel1.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/grabar.png"))); // NOI18N
        btnguardar.setContentAreaFilled(false);
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, -1, 40));

        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Editar.png"))); // NOI18N
        btnmodificar.setContentAreaFilled(false);
        jPanel1.add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnbuscar.setContentAreaFilled(false);
        jPanel1.add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, -1, -1));

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/flecha-circular.png"))); // NOI18N
        btncancelar.setContentAreaFilled(false);
        jPanel1.add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, -1, -1));

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exit.png"))); // NOI18N
        btnsalir.setToolTipText("Presiona \"ESC\" para salir");
        btnsalir.setContentAreaFilled(false);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        btnsalir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnsalirKeyPressed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CODIGO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 110, 20));
        jPanel1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 200, -1));
        jPanel1.add(txtcontrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 200, -1));
        jPanel1.add(txtmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 200, -1));

        btnmostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.png"))); // NOI18N
        btnmostrar.setContentAreaFilled(false);
        btnmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnmostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 40, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnsalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnsalirKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
             int r=JOptionPane.showConfirmDialog(null, "DESEA CERRAR EL FORMULARIO","PREGUNTA",0);
             if (r==0) {
                 super.dispose();
             }else{
                 
             }
             
         }
    }//GEN-LAST:event_btnsalirKeyPressed

    private void btnmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarActionPerformed
        if (mostrar) {
            txtcontrasena.setVisible(false);
            txtmostrar.setVisible(true);
            txtmostrar.setText(txtcontrasena.getText());
             mostrar=false;
        }else{
            txtcontrasena.setVisible(true);
            txtmostrar.setVisible(false);
            txtcontrasena.setText(txtmostrar.getText());
            mostrar=true;
        }
            
    }//GEN-LAST:event_btnmostrarActionPerformed

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
            java.util.logging.Logger.getLogger(usuarioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usuarioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usuarioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usuarioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usuarioVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnaceptar;
    public javax.swing.JButton btnbuscar;
    public javax.swing.JButton btncancelar;
    public javax.swing.JButton btnguardar;
    public javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnmostrar;
    public javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<String> cmbpersonal;
    public javax.swing.JComboBox<String> cmbtipo;
    public javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JRadioButton optactivo;
    public javax.swing.JRadioButton optinactivo;
    public javax.swing.JTable tablausuario;
    public javax.swing.JTextField txtbuscarusuario;
    public javax.swing.JTextField txtcodigo;
    public javax.swing.JPasswordField txtcontrasena;
    public javax.swing.JTextField txtmostrar;
    public javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
