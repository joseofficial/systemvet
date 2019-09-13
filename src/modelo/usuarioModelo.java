/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.usuarioVista;

/**
 *
 * @author Santa206
 */
public class usuarioModelo {

    private String codigo;
    private String nombre;
    private String contrasena;
    private String tipo;
    private String estado;
    private Integer idpersonal;
    DefaultTableModel modelousuario;

    ResultSet rs;
    Statement st;

    usuarioVista vista;

    public usuarioModelo(usuarioVista vista) {
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public void limpiar() {
        vista.txtcodigo.setText("");
        vista.txtnombre.setText("");
        vista.txtcontrasena.setText("");
        vista.optactivo.setEnabled(false);
        vista.optinactivo.setEnabled(false);
        vista.cmbpersonal.setSelectedIndex(0);
        vista.cmbtipo.setSelectedIndex(0);

    }

    public void activabotones(boolean n, boolean g, boolean m, boolean b, boolean c) {
        vista.btnnuevo.setEnabled(n);
        vista.btnguardar.setEnabled(g);
        vista.btnmodificar.setEnabled(m);
        vista.btnbuscar.setEnabled(b);
        vista.btncancelar.setEnabled(c);
    }

    public void bloqueardatos() {
        vista.txtcodigo.setEnabled(false);
        vista.txtnombre.setEnabled(false);
        vista.txtcontrasena.setEnabled(false);
        vista.optactivo.setEnabled(false);
        vista.optinactivo.setEnabled(false);
        vista.cmbpersonal.setEnabled(false);
        vista.cmbtipo.setEnabled(false);
    }

    public void desbloqueardatos() {

        vista.txtnombre.setEnabled(true);
        vista.txtcontrasena.setEnabled(true);
        vista.optactivo.setEnabled(true);
        vista.optinactivo.setEnabled(true);
        vista.cmbpersonal.setEnabled(true);
        vista.cmbtipo.setEnabled(true);
    }

    public void inicio() {
        vista.setTitle("USUARIO");
        vista.setLocationRelativeTo(null);
        vista.setSize(642, 512);
        vista.setVisible(true);
        cargarcombo();
        modelousuario = (DefaultTableModel) vista.tablausuario.getModel();
        bloqueardatos();
        activabotones(true, false, false, true, true);

    }

    public void insertar() {
        String sql = "insert into usuario(idusuario,usu_nombre,usu_clave,usu_tipo,usu_estado,idpersonal) values('" + codigo + "','" + nombre + "','" + contrasena + "','" + tipo + "','" + estado + "','" + idpersonal + "')";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ALMACENADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR");
        }
    }

    public void modificar() {
        String sql = "update usuario set usu_nombre='" + nombre + "',usu_clave='" + contrasena + "',usu_tipo='" + tipo + "'"
                + ",usu_estado='" + estado + "',idpersonal='" + idpersonal + "'"
                + "where idusuario='" + codigo + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
        } catch (Exception e) {
        }
    }

    public void generarcodigo() {
        String sql = "select ifnull(max(idusuario),0)+1 as cod from usuario";

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

    public void cargarcombo() {
        String sql = "select * from personal";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                vista.cmbpersonal.addItem(rs.getString("per_nombre") + " " + rs.getString("per_apellido"));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
    }

    public void mostrarbuscador() {
        vista.jDialog1.setSize(707, 439);
        vista.jDialog1.setLocationRelativeTo(null);
        vista.jDialog1.setVisible(true);
        vista.txtbuscarusuario.setText("");
        vista.txtbuscarusuario.requestFocus();

    }

    public void transferirdatos() {
        int fila = vista.tablausuario.getSelectedRow();
        vista.txtcodigo.setText(vista.tablausuario.getValueAt(fila, 0).toString());
        vista.txtnombre.setText(vista.tablausuario.getValueAt(fila, 1).toString());
        vista.txtcontrasena.setText(vista.tablausuario.getValueAt(fila, 2).toString());
        vista.cmbtipo.setSelectedItem(vista.tablausuario.getValueAt(fila, 3));
        if (vista.tablausuario.getValueAt(fila, 4).equals("ACTIVO")) {
            vista.optactivo.setSelected(true);
        } else {
            vista.optinactivo.setSelected(true);
        }

        vista.cmbpersonal.setSelectedItem(vista.tablausuario.getValueAt(fila, 6).toString());

    }

    public void cargarusuarios(String filtro) {
        String sql = "select * from usuario u inner join personal p on p.idpersonal=u.idpersonal "
                + "where usu_nombre like'" + filtro + "%'";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelousuario.setRowCount(0);

            while (rs.next()) {
                modelousuario.addRow(new Object[]{
                    rs.getString("idusuario"),
                    rs.getString("usu_nombre"),
                    rs.getString("usu_clave"),
                    rs.getString("usu_tipo"),
                    rs.getString("usu_estado"),
                    rs.getString("p.idpersonal"),
                    rs.getString("p.per_nombre") + " " + rs.getString("per_apellido")

                });
            }
            vista.tablausuario.setModel(modelousuario);
            st.close();
            rs.close();
        } catch (Exception e) {
        }
    }
}
