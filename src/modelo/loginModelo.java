/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import vista.login;
import vista.menuprincipal;

/**
 *
 * @author Santa206
 */
public class loginModelo {

    private String usuario;
    private String clave;
    Statement st;
    ResultSet rs;
    login vista;

    public loginModelo(login vista) {
        this.vista = vista;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void inicio() {

        vista.setLocationRelativeTo(null);
        vista.setTitle("CONTROL DE ACCESO");
////        vista.txtusuario.setEnabled(false);
//        vista.txtclave.setEnabled(false);
        vista.btningresar.setEnabled(false);
        

    }

    public void acceder() {
        String sql = "select * from usuario where usu_nombre='" + vista.txtusuario.getText() + "' and usu_clave='" + vista.txtclave.getText() + "'";
        String estado = "";
        String tipo = "";
        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            rs.next();

            if (rs.getRow() == 0) {
                JOptionPane.showMessageDialog(null, "USUARIO Y/O CONTRASEÑA NO SON VÁLIDAS");

            } else {
                estado = rs.getString("usu_estado");
                tipo = rs.getString("usu_tipo");
                if (estado.equals("ACTIVO") && tipo.equals("ADMINISTRADOR")) {
                    menuprincipal menu = new menuprincipal();
                    //menu.txtidpersonal.setText(rs.getString("idpersonal"));
                    menu.txtnombreusuario.setText(rs.getString("usu_nombre"));
                    menu.txttipodepersonal.setText(rs.getString("usu_tipo"));
                    menu.setVisible(true);
                    vista.dispose();

                } else if (estado.equals("ACTIVO")&&tipo.equals("INVITADO")) {
                    menuprincipal menu = new menuprincipal();
                    //menu.txtidpersonal.setText(rs.getString("idpersonal"));
                    menu.txtnombreusuario.setText(rs.getString("usu_nombre"));
                    menu.txttipodepersonal.setText(rs.getString("usu_tipo"));
                    menu.setVisible(true);
                    vista.dispose();
                    menu.btnclientes.setEnabled(false);
                    menu.btnpersonal.setEnabled(false);
                    menu.btnproveedor.setEnabled(false);
                    menu.btnproductos.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "USUARIO INVITADO");

                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO INACTIVO");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(loginModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

