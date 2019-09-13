        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.panelfondo;
import vista.personalVista;

/**
 *
 * @author ALUMNO
 */
public class personalModelo {

    private String codigo;
    private String nombre;
    private String apellido;
    private String ci;
    private String direccion;
    private String telefono;

    Statement st = null;
    DefaultTableModel modelopersonal;
    DefaultTableModel modelofiltro;
    ResultSet rs = null;

    personalVista vista;

    public personalModelo(personalVista vista) {

        this.vista = vista;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void limpiar() {
        vista.txtcodigo.setText("");
        vista.txtnombre.setText("");
        vista.txtapellido.setText("");
        vista.txtci.setText("");
        vista.txtdireccion.setText("");
        vista.txttelefono.setText("");

    }

    public void bloqueardatos() {
        vista.txtcodigo.setEnabled(false);
        vista.txtnombre.setEnabled(false);
        vista.txtapellido.setEnabled(false);
        vista.txtci.setEnabled(false);
        vista.txtdireccion.setEnabled(false);
        vista.txttelefono.setEnabled(false);
    }

    public void desbloqueardatos() {
        vista.txtcodigo.setEnabled(false);
        vista.txtnombre.setEnabled(true);
        vista.txtapellido.setEnabled(true);
        vista.txtci.setEnabled(true);
        vista.txtdireccion.setEnabled(true);
        vista.txttelefono.setEnabled(true);
    }

    public void activaBotones(boolean n, boolean g, boolean m, boolean e, boolean b, boolean c) {
        vista.btnnuevo.setEnabled(n);
        vista.btnguardar.setEnabled(g);
        vista.btneliminar.setEnabled(e);
        vista.btnbuscar.setEnabled(b);
        vista.btncancelar.setEnabled(c);
        vista.btnmodificar.setEnabled(m);

    }

    public void inicio() {
//        panelfondo F= new panelfondo();// Instancia
//        vista.add(F, BorderLayout.CENTER);
        vista.setLocationRelativeTo(null);
        vista.setTitle("FORMULARIO PERSONAL");
        bloqueardatos();
        activaBotones(true, false, false, false, true, true);
        modelopersonal = (DefaultTableModel) vista.tablapersonal.getModel();
        cargartabla();
        modelofiltro = (DefaultTableModel) vista.tablafiltro.getModel();

    }

    public void insertar() {
        String sql = "insert into personal(idpersonal,per_nombre,per_apellido,per_ci,per_telefono,per_direccion) "
                + "values ('" + codigo + "','" + nombre + "','" + apellido + "','" + ci + "','" + telefono + "','" + direccion + "')";
        try {
            //abrir la conexion
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");

        } catch (SQLException ex) {
            Logger.getLogger(personalModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargartabla() {
        String sql = "select * from personal";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelopersonal.setRowCount(0);
            while (rs.next()) {
                modelopersonal.addRow(new Object[]{
                    rs.getString("idpersonal"),
                    rs.getString("per_nombre"),
                    rs.getString("per_apellido"),
                    rs.getString("per_ci"),
                    rs.getString("per_telefono"),
                    rs.getString("per_direccion")
                });

            }
            vista.tablapersonal.setModel(modelopersonal);
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(personalModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void transferirPersonal() {
        int filaseleccionada = vista.tablapersonal.getSelectedRow();

        String codigo_, nombre_, apellido_, ci_, telefono_, direccion_;

        codigo_ = vista.tablapersonal.getValueAt(filaseleccionada, 0).toString();
        nombre_ = vista.tablapersonal.getValueAt(filaseleccionada, 1).toString();
        apellido_ = vista.tablapersonal.getValueAt(filaseleccionada, 2).toString();
        ci_ = vista.tablapersonal.getValueAt(filaseleccionada, 3).toString();
        telefono_ = vista.tablapersonal.getValueAt(filaseleccionada, 4).toString();
        direccion_ = vista.tablapersonal.getValueAt(filaseleccionada, 5).toString();

        vista.txtcodigo.setText(codigo_);
        vista.txtnombre.setText(nombre_);
        vista.txtapellido.setText(apellido_);
        vista.txtci.setText(ci_);
        vista.txttelefono.setText(telefono_);
        vista.txtdireccion.setText(direccion_);

        bloqueardatos();
        activaBotones(false, false, true, true, true, true);
    }

    public void modificar() {

        String sql = "update personal \n"
                + " set per_nombre='" + nombre + "',per_apellido='" + apellido + "',per_ci='" + ci + "',per_telefono='" + telefono + "',per_direccion='" + direccion + "'\n "
                + "where idpersonal='" + codigo + "'";
        try {
            //abrir la conexion
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");

        } catch (SQLException ex) {
            Logger.getLogger(personalModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminar() {
        String sql = "delete from personal where idpersonal='" + codigo + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");

        } catch (SQLException ex) {
            Logger.getLogger(personalModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mostrarbuscador() {
        vista.jDialog1.setSize(820, 544);
        vista.jDialog1.setLocationRelativeTo(null);
        vista.jDialog1.setVisible(true);
        vista.txtbuscar.setText("");
        vista.txtbuscar.requestFocus();
    }

    public void cargartablafiltro(String filtro) {
        String sql = "select * from personal where per_nombre like'" + filtro + "%'";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelofiltro.setRowCount(0);
            while (rs.next()) {
                modelofiltro.addRow(new Object[]{
                    rs.getString("idpersonal"),
                    rs.getString("per_nombre"),
                    rs.getString("per_apellido"),
                    rs.getString("per_ci"),
                    rs.getString("per_telefono"),
                    rs.getString("per_direccion")
                });

            }
            vista.tablafiltro.setModel(modelofiltro);
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(personalModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void transferirPersonal2() {
        int filaseleccionada = vista.tablafiltro.getSelectedRow();

        String codigo_, nombre_, apellido_, ci_, telefono_, direccion_;

        codigo_ = vista.tablafiltro.getValueAt(filaseleccionada, 0).toString();
        nombre_ = vista.tablafiltro.getValueAt(filaseleccionada, 1).toString();
        apellido_ = vista.tablafiltro.getValueAt(filaseleccionada, 2).toString();
        ci_ = vista.tablafiltro.getValueAt(filaseleccionada, 3).toString();
        telefono_ = vista.tablafiltro.getValueAt(filaseleccionada, 4).toString();
        direccion_ = vista.tablafiltro.getValueAt(filaseleccionada, 5).toString();

        vista.txtcodigo.setText(codigo_);
        vista.txtnombre.setText(nombre_);
        vista.txtapellido.setText(apellido_);
        vista.txtci.setText(ci_);
        vista.txttelefono.setText(telefono_);
        vista.txtdireccion.setText(direccion_);

        bloqueardatos();
        activaBotones(false, false, true, true, true, true);
    }
    
    public void generarcodigo(){
        String sql="Select ifnull(max(idpersonal),0)+1 as cod from personal";
        
        try {
            st=conexion.conexion.sta(st);
            rs=st.executeQuery(sql);
            rs.next();
            vista.txtcodigo.setText(rs.getString("cod"));
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(personalModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
 