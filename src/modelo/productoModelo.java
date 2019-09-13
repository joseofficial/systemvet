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

import vista.productoVista;

/**
 *
 * @author Santa206
 */
public class productoModelo {
    private String codigo;
    private String nombre;
    private String precio;
    private String cantidad;
    private String iva;
    private Integer proveedor;
    
    productoVista vista;
    Statement st=null;
    ResultSet rs=null;
    DefaultTableModel modeloproducto;
    
    public productoModelo(productoVista vista) {
        this.vista = vista;
    }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

   
    
    public void limpiar() {
        vista.txtcodigo.setText("");
        vista.txtnombre.setText("");
        vista.txtprecio.setText("");
        vista.txtcantidad.setText("");
        vista.txtiva.setText("");
        vista.cbmproveedor.setSelectedIndex(0);

    }

    public void bloqueardatos() {
        vista.txtcodigo.setEnabled(false);
        vista.txtnombre.setEnabled(false);
        vista.txtprecio.setEnabled(false);
        vista.txtcantidad.setEnabled(false);
        vista.txtiva.setEnabled(false);
        vista.cbmproveedor.setEnabled(false);
    }

    public void desbloqueardatos() {
        vista.txtcodigo.setEnabled(false);
        vista.txtnombre.setEnabled(true);
        vista.txtprecio.setEnabled(true);
        vista.txtcantidad.setEnabled(true);
        vista.txtiva.setEnabled(true);
        vista.cbmproveedor.setEnabled(true);
    }

    public void activaBotones(boolean n, boolean g, boolean m, boolean e, boolean b, boolean c) {
        vista.btnnuevo.setEnabled(n);
        vista.btnguardar.setEnabled(g);
        //vista.btnanular.setEnabled(e);
        vista.btnbuscar.setEnabled(b);
        vista.btncancelar.setEnabled(c);
        vista.btnmodificar.setEnabled(m);

    }
    public void inicio(){
//        panelfondo F= new panelfondo();
//        vista.add(F, BorderLayout.CENTER);
        cargarcombo();
        vista.setLocationRelativeTo(null);
        vista.setTitle("FORMULARIO PRODUCTOS");
        bloqueardatos();
        activaBotones(true, false, false, false, true, true);
        modeloproducto = (DefaultTableModel) vista.tablaproducto.getModel();
    }
    
    public void insertar(){
        String sql="insert into producto (idproducto,pro_nombre,pro_precio,pro_cantidad,pro_iva,idproveedor ) ";
        sql+=" values ('"+codigo+"','"+nombre+"','"+precio+"','"+cantidad+"','"+iva+"','"+proveedor+"')";
        
        try {
            st=conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "PRODUCTO REGISTRADO");
        } catch (SQLException ex) {
            Logger.getLogger(productoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void generarcodigo(){
        String sql="select ifnull(max(idproducto),0)+1 as cod from producto";
        
        try {
            st=conexion.conexion.sta(st);
            rs=st.executeQuery(sql);
            rs.next();
            vista.txtcodigo.setText(rs.getString("cod"));
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(productoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void cargarcombo(){
        String sql="SELECT * FROM proveedor ";
        
        try {
            st=conexion.conexion.sta(st);
            rs=st.executeQuery(sql);
            
            while (rs.next()) {   
                
                vista.cbmproveedor.addItem(rs.getString("prov_nombre"));
                
            }
            st.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(productoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modificar(){
        String sql="update producto set pro_nombre='"+nombre+"',pro_precio='"+precio+"',pro_cantidad='"+cantidad+"',pro_iva='"+iva+"',idproveedor='"+proveedor+"' where idproducto='"+codigo+"'";
        
        try {
            st=conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
        } catch (SQLException ex) {
            Logger.getLogger(productoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void mostrarbuscador(){
        vista.buscadorproducto.setSize(736,479);
        vista.buscadorproducto.setVisible(true);
        vista.buscadorproducto.setLocationRelativeTo(null);
        vista.txtbuscarproducto.setText("");
        vista.txtbuscarproducto.requestFocus();
    }
    public void filtroproducto(String filtro){
        String sql="select * from producto p inner join proveedor pv on p.idproveedor=pv.idproveedor ";
                sql+= " where pro_nombre like'"+filtro+"%'";
        
        try {
            st=conexion.conexion.sta(st);
            rs=st.executeQuery(sql);
            modeloproducto.setRowCount(0);
            while (rs.next()) {                
                modeloproducto.addRow(new Object[]{
                    rs.getString("idproducto"),
                    rs.getString("pro_nombre"),
                    rs.getString("pro_precio"),
                    rs.getString("pro_cantidad"),
                    rs.getString("pro_iva"),
                    rs.getString("pv.idproveedor"),
                    rs.getString("pv.prov_nombre")
                });
                
            }
            vista.tablaproducto.setModel(modeloproducto);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(productoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void transferirproducto(){
        int fila=vista.tablaproducto.getSelectedRow();
        
        vista.txtcodigo.setText(vista.tablaproducto.getValueAt(fila, 0).toString());  
        vista.txtnombre.setText(vista.tablaproducto.getValueAt(fila, 1).toString());
        vista.txtprecio.setText(vista.tablaproducto.getValueAt(fila, 2).toString());
        vista.txtcantidad.setText(vista.tablaproducto.getValueAt(fila, 3).toString());
        vista.txtiva.setText(vista.tablaproducto.getValueAt(fila, 4).toString());
        vista.cbmproveedor.setSelectedItem(vista.tablaproducto.getValueAt(fila, 6).toString());
        
       
        
        
        bloqueardatos();
        activaBotones(false, false, true, true, true, true);
        
    }
    
    
} 
