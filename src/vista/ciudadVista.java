/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Santa206
 */
public class ciudadVista extends javax.swing.JFrame {

    /**
     * Creates new form ciudadVista
     */
    public ciudadVista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaciudad = new javax.swing.JTable();
        btnaceptar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("BUSCAR");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));
        jPanel2.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 390, -1));

        tablaciudad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaciudad.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaciudad);
        if (tablaciudad.getColumnModel().getColumnCount() > 0) {
            tablaciudad.getColumnModel().getColumn(0).setResizable(false);
            tablaciudad.getColumnModel().getColumn(0).setPreferredWidth(75);
            tablaciudad.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 90));

        btnaceptar.setText("ACEPTAR");
        jPanel2.add(btnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, -1, -1));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("CIUDADES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel2.setText("CODIGO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel3.setText("DESCRIPCIÓN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 80, -1));
        jPanel1.add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 140, -1));
        jPanel1.add(txtdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 180, -1));

        btnnuevo.setText("NUEVO");
        jPanel1.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        btnmodificar.setText("MODIFICAR");
        jPanel1.add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, -1, -1));

        btneliminar.setText("ELIMINAR");
        jPanel1.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, -1, -1));

        btnbuscar.setText("BUSCAR");
        jPanel1.add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        btncancelar.setText("CANCELAR");
        jPanel1.add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, -1));

        jButton7.setText("SALIR");
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, -1, -1));

        btnguardar.setText("GUARDAR");
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ciudadVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ciudadVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ciudadVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ciudadVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ciudadVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnaceptar;
    public javax.swing.JButton btnbuscar;
    public javax.swing.JButton btncancelar;
    public javax.swing.JButton btneliminar;
    public javax.swing.JButton btnguardar;
    public javax.swing.JButton btnmodificar;
    public javax.swing.JButton btnnuevo;
    public javax.swing.JButton jButton7;
    public javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaciudad;
    public javax.swing.JTextField txtbuscar;
    public javax.swing.JTextField txtcodigo;
    public javax.swing.JTextField txtdescripcion;
    // End of variables declaration//GEN-END:variables
}
