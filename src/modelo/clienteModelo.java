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

import vista.clientesVista;
import vista.panelfondo;

/**
 *
 * @author Santa206
 */
public class clienteModelo {

    private String codigo;
    private String nombre;
    private String apellido;
    private String ci;
    private String direccion;
    private String telefono;

    Statement st = null;
    DefaultTableModel modeloCliente;
    ResultSet rs = null;

    clientesVista vista;

    public clienteModelo(clientesVista vista) {
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

    public void insertar() {
        String sql = "insert into cliente (idcliente,cli_nombre,cli_apellido, cli_ci, cli_telefono,cli_direccion) "
                + "values('" + codigo + "','" + nombre + "','" + apellido + "','" + ci + "','" + telefono + "','" + direccion + "')";
        try {
            st=conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");
        } catch (SQLException ex) {
            Logger.getLogger(clienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inicio() {
//        panelfondo F= new panelfondo();
//        vista.add(F, BorderLayout.CENTER);
        
        vista.setLocationRelativeTo(null);
        vista.setTitle("FORMULARIO CLIENTES");
        bloqueardatos();
        activaBotones(true, false, false, false, true, true);
        modeloCliente = (DefaultTableModel) vista.tablafiltro.getModel();

    }

    public void modificar() {
        String sql = "update cliente set cli_nombre='" + nombre + "', cli_apellido='" + apellido + "',cli_ci='" + ci + "',cli_telefono='" + telefono + "',"
                + "cli_direccion='" + direccion + "'"
                + " where idcliente='" + codigo + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
        } catch (SQLException ex) {
            Logger.getLogger(clienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminar() {
        String sql = "delete from cliente where idcliente='" + codigo + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                
        } catch (SQLException ex) {
            Logger.getLogger(clienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void cargarTablaFiltro(String filtro){
        String sql="select * from cliente where cli_nombre like'"+filtro+"%'";
        
            try {
            st=conexion.conexion.sta(st);
            rs=st.executeQuery(sql);
            modeloCliente.setRowCount(0);
            while (rs.next()) {
            modeloCliente.addRow(new Object[]{
                rs.getString("idcliente"),
                rs.getString("cli_nombre"),
                rs.getString("cli_apellido"),
                rs.getString("cli_ci"),
                rs.getString("cli_telefono"),
                rs.getString("cli_direccion")
            });
                
            }
            vista.tablafiltro.setModel(modeloCliente);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(clienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void transferirCliente(){
        int fila= vista.tablafiltro.getSelectedRow();
        
        String nombre_, apellido_, ci_,telefono_, direccion_, codigo_;
        
        codigo_=vista.tablafiltro.getValueAt(fila, 0).toString();
        nombre_=vista.tablafiltro.getValueAt(fila, 1).toString();
        apellido_=vista.tablafiltro.getValueAt(fila, 2).toString();
        ci_=vista.tablafiltro.getValueAt(fila, 3).toString();
        telefono_=vista.tablafiltro.getValueAt(fila, 4).toString();
        direccion_=vista.tablafiltro.getValueAt(fila, 5).toString();
        
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
        String sql="Select ifnull(max(idcliente),0)+1 as cod from cliente";
        
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
     public void mostrarbuscador() {
        vista.jDialog1.setSize(740, 523);
        vista.jDialog1.setLocationRelativeTo(null);
        vista.jDialog1.setVisible(true);
        vista.txtbuscar.setText("");
        vista.txtbuscar.requestFocus();
    }
}
