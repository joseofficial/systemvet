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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.ciudadVista;

/**
 *
 * @author Santa206
 */
public class ciudadModelo {

    private String codigo;
    private String descripcion;
    DefaultTableModel modelociudad;
    ResultSet rs = null;
    Statement st = null;

    ciudadVista vista;

    public ciudadModelo(ciudadVista vista) {
        this.vista = vista;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void limpiar() {
        vista.txtcodigo.setText("");
        vista.txtdescripcion.setText("");
        vista.txtcodigo.setEnabled(false);
        vista.txtdescripcion.setEnabled(false);
    }

    public void generarcodigo() {
        String sql = "select ifnull(max(idciudad),0)+1 as cod from ciudad";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            rs.next();
            vista.txtcodigo.setText(rs.getString("cod"));
            st.close();
            rs.close();

        } catch (Exception e) {

        }

    }

    public void inicio() {
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setSize(589, 361);
        vista.setTitle("CIUDAD");
        vista.txtdescripcion.setEnabled(false);
        modelociudad = (DefaultTableModel) vista.tablaciudad.getModel();
        limpiar();
        activabotones(true, false, false, false, true, true);

    }

    public void activabotones(boolean n, boolean g, boolean m, boolean e, boolean b, boolean c) {
        vista.btnnuevo.setEnabled(n);
        vista.btnguardar.setEnabled(g);
        vista.btnmodificar.setEnabled(m);
        vista.btneliminar.setEnabled(e);
        vista.btnbuscar.setEnabled(b);
        vista.btncancelar.setEnabled(c);
    }

    public void insertar() {
        String sql = "insert into ciudad(idciudad,ciu_descripcion)values('" + codigo + "','" + descripcion + "')";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");

        } catch (Exception e) {
        }
    }

    public void modificar() {
        String sql = "update ciudad set ciu_descripcion='" + descripcion + "' where idciudad='" + codigo + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");

        } catch (Exception e) {
        }

    }

    public void eliminar() {
        String sql = "delete from ciudad where idciudad='" + codigo + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");

        } catch (SQLException ex) {
            Logger.getLogger(clienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarTablaFiltro(String filtro) {
        String sql = "select * from ciudad where ciu_descripcion like'" + filtro + "%'";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelociudad.setRowCount(0);
            while (rs.next()) {
                modelociudad.addRow(new Object[]{
                    rs.getString("idciudad"),
                    rs.getString("ciu_descripcion")

                });

            }
            vista.tablaciudad.setModel(modelociudad);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(clienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void transferirCiudad() {
        int fila = vista.tablaciudad.getSelectedRow();

        
        String codigo_=vista.tablaciudad.getValueAt(fila, 0).toString();
        String descripcion_=vista.tablaciudad.getValueAt(fila, 1).toString();
        
        vista.txtcodigo.setText(codigo_);
        vista.txtdescripcion.setText(descripcion_);
        
        vista.txtdescripcion.setEnabled(false);
        activabotones(false, false, true, true, false, true);
    }
    public void mostrarbuscador() {
        vista.jDialog1.setSize(547,384);
        vista.jDialog1.setLocationRelativeTo(null);
        vista.jDialog1.setVisible(true);
        vista.txtbuscar.setText("");
        vista.txtbuscar.requestFocus();
    }
}
