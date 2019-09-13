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
import vista.proveedorVista;

/**
 *
 * @author Santa206
 */
public class proveedorModelo {

    String codigo;
    String nombre;
    String ruc;
    String direccion;
    String telefono;
    String correo;

    Statement st = null;
    DefaultTableModel modelofiltro;
    ResultSet rs = null;

    proveedorVista vista;

    public proveedorModelo(proveedorVista vista) {
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void limpiar() {
        vista.txtcodigo.setText("");
        vista.txtnombre.setText("");
        vista.txtruc.setText("");
        vista.txtdireccion.setText("");
        vista.txttelefono.setText("");
        vista.txtcorreo.setText("");

    }

    public void bloqueardatos() {
        vista.txtcodigo.setEnabled(false);
        vista.txtnombre.setEnabled(false);
        vista.txtruc.setEnabled(false);
        vista.txtdireccion.setEnabled(false);
        vista.txttelefono.setEnabled(false);
        vista.txtcorreo.setEnabled(false);
    }

    public void desbloqueardatos() {
        vista.txtcodigo.setEnabled(false);
        vista.txtnombre.setEnabled(true);
        vista.txtruc.setEnabled(true);
        vista.txtdireccion.setEnabled(true);
        vista.txttelefono.setEnabled(true);
        vista.txtcorreo.setEnabled(true);
    }

    public void activaBotones(boolean n, boolean g, boolean m, boolean e, boolean b, boolean c) {
        vista.btnnuevo.setEnabled(n);
        vista.btnguardar.setEnabled(g);
        vista.btneliminar.setEnabled(e);
        vista.btnbuscar.setEnabled(b);
        vista.btncancelar.setEnabled(c);
        vista.btnmodificar.setEnabled(m);

    }
    public void insertar(){
        String sql="insert into proveedor(idproveedor,prov_nombre,prov_ruc,prov_direccion,prov_telefono,prov_correo)"
                + "values('"+codigo+"','"+nombre+"','"+ruc+"','"+direccion+"','"+telefono+"','"+correo+"')";
        
        try {
            st=conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null,"REGISTRO ALMACENADO");
        } catch (SQLException ex) {
            Logger.getLogger(proveedorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void inicio(){
        panelfondo F=new panelfondo();
        vista.add(F, BorderLayout.CENTER);
        vista.setLocationRelativeTo(null);
        vista.setTitle("FORMULARIO PROVEEDOR");
        bloqueardatos();
        activaBotones(true, false, false, false, true, true);
        modelofiltro=(DefaultTableModel) vista.tablafiltro.getModel();
    
    }
    public void modificar(){
        String sql="update proveedor set prov_nombre='"+nombre+"',prov_ruc='"+ruc+"',prov_direccion='"+direccion+"',"
                + "prov_telefono='"+telefono+"',prov_correo='"+correo+"'"
                + "where idproveedor='"+codigo+"'";
        
        try {
            st=conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
        } catch (SQLException ex) {
            Logger.getLogger(proveedorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void eliminar(){
        String sql="delete from proveedor where idproveedor='"+codigo+"'";
        
        try {
            st=conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
            
        } catch (SQLException ex) {
            Logger.getLogger(proveedorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void mostrarbuscador(){
        vista.jDialog1.setSize(743, 457);
        vista.jDialog1.setLocationRelativeTo(null);
        vista.jDialog1.setVisible(true);
        vista.txtbuscar.setText("");
        vista.txtbuscar.requestFocus();
    }
    public void cargatablafiltro(String filtro){
        String sql="select * from proveedor where prov_nombre like'"+filtro+"%'";
        
        try {
            st=conexion.conexion.sta(st);
            rs=st.executeQuery(sql);
            modelofiltro.setRowCount(0);
            while (rs.next()) {                
                modelofiltro.addRow(new Object[]{
                        rs.getString("idproveedor"),
                        rs.getString("prov_nombre"),
                        rs.getString("prov_ruc"),
                        rs.getString("prov_direccion"),
                        rs.getString("prov_telefono"),
                        rs.getString("prov_correo")
                              
                });
            }
            vista.tablafiltro.setModel(modelofiltro);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(proveedorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void transferirproveedor(){
        int fila=vista.tablafiltro.getSelectedRow();
        
        String codigo_, nombre_,ruc_,direccion_,telefono_,correo_;
        
        codigo_=vista.tablafiltro.getValueAt(fila, 0).toString();
        nombre_=vista.tablafiltro.getValueAt(fila, 1).toString();
        ruc_=vista.tablafiltro.getValueAt(fila, 2).toString();
        direccion_=vista.tablafiltro.getValueAt(fila, 3).toString();
        telefono_=vista.tablafiltro.getValueAt(fila, 4).toString();
        correo_=vista.tablafiltro.getValueAt(fila, 5).toString();
        
        vista.txtcodigo.setText(codigo_);
        vista.txtnombre.setText(nombre_);
        vista.txtruc.setText(ruc_);
        vista.txtdireccion.setText(direccion_);
        vista.txttelefono.setText(telefono_);
        vista.txtcorreo.setText(correo_);
        bloqueardatos();
        activaBotones(false, false, true, true, true, true);
        
        
    }
    
    public void generarcodigo(){
        
        String sql="select ifnull(max(idproveedor),0)+1 as cod from proveedor";
        
        try {
            st=conexion.conexion.sta(st);
            rs=st.executeQuery(sql);
            rs.next();
            vista.txtcodigo.setText(rs.getString("cod"));
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(proveedorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
