/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Frame;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utilidades.numero;
import vista.facturaCabVista;
import vista.menuprincipal;

/**
 *
 * @author Santa206
 */
public class facturaModelo {

    private String idfactura;
    private String nrofactura;
    private String fecha;
    private String condicion;
    private String estado;
    private String idcliente;
    private String idpersonal;
    DefaultTableModel modelocliente;
    DefaultTableModel modelodetalle;
    DefaultTableModel modeloproducto;
    DefaultTableModel modelobuscafactura;

    Statement st = null;
    ResultSet rs = null;

    facturaCabVista vista;

    public facturaModelo(facturaCabVista vista) {
        this.vista = vista;
    }

    public String getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(String idfactura) {
        this.idfactura = idfactura;
    }

    public String getNrofactura() {
        return nrofactura;
    }

    public void setNrofactura(String nrofactura) {
        this.nrofactura = nrofactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(String idpersonal) {
        this.idpersonal = idpersonal;
    }

    public void bloquearObjetos() {
        vista.txtnumero.setEnabled(false);
        vista.optcontado.setEnabled(false);
        vista.optcredito.setEnabled(false);
        vista.txtidproducto.setEnabled(false);
        vista.txtcantidad.setEnabled(false);
        vista.txtid.setEnabled(false);

    }

    public void desbloquearObjetos() {
        vista.txtnumero.setEnabled(true);
        vista.optcontado.setEnabled(true);
        vista.optcredito.setEnabled(true);
        vista.txtidproducto.setEnabled(true);
        vista.txtcantidad.setEnabled(true);
    }

    public void inicio() {
        vista.setLocationRelativeTo(null);
        vista.setTitle("Factura");
        fecha();
        bloquearObjetos();
        activabotones(false, false, false, true, false, false, true, false, true);
        modelocliente = (DefaultTableModel) vista.tablacliente.getModel();
        modelodetalle = (DefaultTableModel) vista.tabladetalle.getModel();
        modeloproducto = (DefaultTableModel) vista.tablabuscadorproducto.getModel();
        modelobuscafactura = (DefaultTableModel) vista.tablafactura.getModel();
        
    }

    public void activabotones(boolean bc, boolean add, boolean del, boolean n, boolean g, boolean i, boolean b, boolean a, boolean c) {
        vista.btnbuscarcliente.setEnabled(bc);
        vista.btnadd.setEnabled(add);
        vista.btndell.setEnabled(del);
        vista.btnnuevo.setEnabled(n);
        vista.btnguardar.setEnabled(g);
        vista.btnimprimir.setEnabled(i);
        vista.btnbuscar.setEnabled(b);
        vista.btnanular.setEnabled(a);
        vista.btncancelar.setEnabled(c);

    }

    public void generarCodigo() {
        String sql = "Select ifnull(max(idfactura),0)+1 as cod from facturaventa";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            rs.next();
            vista.txtid.setText(rs.getString("cod"));
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarclientefiltro(String filtro) {
        String sql = "select * from cliente where cli_ci like '" + filtro + "%' or cli_nombre like '" + filtro + "%' or cli_apellido like'" + filtro + "'";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelocliente.setRowCount(0);
            while (rs.next()) {
                modelocliente.addRow(new Object[]{
                    rs.getString("idcliente"),
                    rs.getString("cli_nombre"),
                    rs.getString("cli_apellido"),
                    rs.getString("cli_ci")
                });

            }
            vista.tablacliente.setModel(modelocliente);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void transferircliente() {
        int fila = vista.tablacliente.getSelectedRow();

        String cod_, nombre_, apellido_, ci_;

        cod_ = vista.tablacliente.getValueAt(fila, 0).toString();
        nombre_ = vista.tablacliente.getValueAt(fila, 1).toString();
        apellido_ = vista.tablacliente.getValueAt(fila, 2).toString();
        ci_ = vista.tablacliente.getValueAt(fila, 3).toString();

        vista.txtcliente.setText(cod_);
        vista.txtnombre.setText(nombre_);
        vista.txtapellido.setText(apellido_);
        vista.txtruc.setText(ci_);

    }

    public void mostrarcliente() {
        vista.jDialog1.setSize(630, 387);
        vista.jDialog1.setLocationRelativeTo(null);
        vista.jDialog1.setVisible(true);
        vista.txtfiltrocliente.setText("");
        vista.txtfiltrocliente.requestFocus();
//        URL url = getClass().getResource("/imagenes/healthcare.png");
//        ImageIcon img = new ImageIcon(url);
//        vista.setIconImage(img.getImage());
    }

    public void cargarproductoporcodigo(String codigo) {
        String sql = "select * from producto where idproducto='" + codigo + "'";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            rs.next();
            if (rs.getRow() == 0) {
                vista.txtnombreproducto.setText("CODIGO INCORRECTO");
            } else {
                vista.txtnombreproducto.setText(rs.getString("pro_nombre"));
                vista.txtcantidad.requestFocus();
            }
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void añadir(String codigo) {
        String sql = "select * from producto where idproducto='" + codigo + "'";
        String iva = null;
        int precio = 0;
        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            rs.next();
            iva = rs.getString("pro_iva");
            precio = rs.getInt("pro_precio");
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (iva.equals("0")) {
            modelodetalle.addRow(new Object[]{
                vista.txtidproducto.getText(),
                vista.txtnombreproducto.getText(),
                vista.txtcantidad.getText(),
                precio,
                (precio * Integer.parseInt(vista.txtcantidad.getText())),
                "",
                ""

            });

        }
        if (iva.equals("5")) {
            modelodetalle.addRow(new Object[]{
                vista.txtidproducto.getText(),
                vista.txtnombreproducto.getText(),
                vista.txtcantidad.getText(),
                precio,
                "",
                (precio * Integer.parseInt(vista.txtcantidad.getText())),
                ""

            });
        }
        if (iva.equals("10")) {
            modelodetalle.addRow(new Object[]{
                vista.txtidproducto.getText(),
                vista.txtnombreproducto.getText(),
                vista.txtcantidad.getText(),
                precio,
                "",
                "",
                (precio * Integer.parseInt(vista.txtcantidad.getText()))

            });
        }
        vista.tabladetalle.setModel(modelodetalle);
    }

    public void limpiarproducto() {
        vista.txtidproducto.setText("");
        vista.txtcantidad.setText("");
        vista.txtnombreproducto.setText("NOMBRE DE PRODUCTO");
        vista.txtidproducto.requestFocus();
    }

    public void quitar() {
        int filaseleccionada = vista.tabladetalle.getSelectedRow();

        if (filaseleccionada != -1) {
            modelodetalle.removeRow(filaseleccionada);
            total();
        } else {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO");
        }
    }

    public void total() {
        //sumatoria de excenta
        int cantidadfilas = vista.tabladetalle.getRowCount();
        int sumaexenta = 0, suma5 = 0, suma10 = 0;
        for (int i = 0; i < cantidadfilas; i++) {
            if (!vista.tabladetalle.getValueAt(i, 4).equals("")) {
                sumaexenta = sumaexenta + Integer.parseInt(vista.tabladetalle.getValueAt(i, 4).toString());
            }
            if (!vista.tabladetalle.getValueAt(i, 5).equals("")) {
                suma5 = suma5 + Integer.parseInt(vista.tabladetalle.getValueAt(i, 5).toString());
            }
            if (!vista.tabladetalle.getValueAt(i, 6).equals("")) {
                suma10 = suma10 + Integer.parseInt(vista.tabladetalle.getValueAt(i, 6).toString());
            }

        }
        vista.txttotalexenta.setText(Integer.toString(sumaexenta));
        vista.txttotal5.setText(Integer.toString(suma5));
        vista.txttotal10.setText(Integer.toString(suma10));

        int total = suma10 + suma5 + sumaexenta;
        vista.txttotalfactura.setText(Integer.toString(total));

        //liq5%
        int iva5 = suma5 / 21;

        //liq 10%
        int iva10 = suma10 / 11;

        vista.txtliquidacion10.setText(Integer.toString(iva10));
        vista.txtliquidacion5.setText(Integer.toString(iva5));

        //total de IVA
        int totaliva = iva5 + iva10;

        vista.txtliquidaciontotal.setText(Integer.toString(totaliva));
    }

    public void mostrarbuscadorproducto() {

        vista.jDialog3.setSize(730, 510);
        vista.jDialog3.setLocationRelativeTo(null);
        vista.txtbuscarproducto.setText("");
        vista.txtbuscarproducto.requestFocus();
        vista.jDialog3.setVisible(true);
        URL url = getClass().getResource("/imagenes/healthcare.png");
        ImageIcon img = new ImageIcon(url);
        vista.setIconImage(img.getImage());

    }

    public void cargartablabuscadorproducto(String filtro) {
        String sql = "select * from producto where pro_nombre like'" + filtro + "%'";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            modeloproducto.setRowCount(0);
            while (rs.next()) {
                modeloproducto.addRow(new Object[]{
                    rs.getString("idproducto"),
                    rs.getString("pro_nombre"),
                    rs.getString("pro_cantidad")

                });

            }
            vista.tablabuscadorproducto.setModel(modeloproducto);
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void transferirdebuscadorproducto() {
        int filaseleccionada = vista.tablabuscadorproducto.getSelectedRow();

        vista.txtidproducto.setText(vista.tablabuscadorproducto.getValueAt(filaseleccionada, 0).toString());
        vista.txtnombreproducto.setText(vista.tablabuscadorproducto.getValueAt(filaseleccionada, 1).toString());
        vista.txtcantidad.requestFocus();
        vista.jDialog3.setVisible(false);
    }

    public void fecha() {
        String cdias, cmeses, fechaa;

        Calendar cal = Calendar.getInstance();
        int dias = cal.get(Calendar.DATE);
        int meses = cal.get(Calendar.MONTH) + 1;
        int anhos = cal.get(Calendar.YEAR);

        if (dias < 10) {
            cdias = "0" + dias;
        } else {
            cdias = "0" + dias;
        }
        if (meses < 10) {
            cmeses = "0" + meses;

        } else {
            cmeses = String.valueOf(meses);
        }
        fechaa = anhos + "-" + cmeses + "-" + cdias;

        vista.txtfecha.setText(fechaa);
    }

    public void guardarfactura() {
        String sql = "insert into facturaventa(idfactura,fac_fecha,fac_condicion,fac_estado,idpersonal,"
                + "idcliente,fac_nro) values('" + idfactura + "','" + fecha + "','" + condicion + "','" + estado + "','" + idpersonal + "','" + idcliente + "','" + nrofactura + "')";
        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void detallefactura(String producto, String cantidad, String precio) {
        String sql = "insert into detallefactura (idfactura,idproducto,fac_cantidad,fec_precio) "
                + "values('" + idfactura + "','" + producto + "','" + cantidad + "','" + precio + "')";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void limpiar() {
        vista.txtnumero.setText("");
        vista.txtid.setText("");
        vista.txtcliente.setText("");
        vista.txtnombre.setText("");
        vista.txtapellido.setText("");
        vista.txtruc.setText("");
        vista.txtidproducto.setText("");
        vista.txtnombreproducto.setText("NOMBRE PRODUCTO");
        vista.txtcantidad.setText("");
        vista.txttotal10.setText("");
        vista.txttotal5.setText("");
        vista.txttotalexenta.setText("");
        vista.txtliquidacion10.setText("");
        vista.txtliquidacion5.setText("");
        vista.txtliquidaciontotal.setText("");
        vista.txttotalfactura.setText("");
        vista.txtestado.setText("PENDIENTE");

        vista.optcontado.setEnabled(false);
        vista.optcredito.setEnabled(false);

    }

    public void llamarinforme() {
        int num = Integer.parseInt(vista.txttotalfactura.getText());
        numero n = new numero();
        String letra = n.convertirLetras(num) + ".-";
        Connection conn = null;
        String RUTALOCAL = System.getProperty("user.dir");
        try {
            conn = conexion.conexion.Enlace(conn);
            HashMap parametros = new HashMap();
            parametros.put("factura", vista.txtid.getText());
            parametros.put("totalexcenta", vista.txttotalexenta.getText());
            parametros.put("total5", vista.txttotal5.getText());
            parametros.put("total10", vista.txttotal10.getText());
            parametros.put("liq5", vista.txtliquidacion5.getText());
            parametros.put("liq10", vista.txtliquidacion10.getText());
            parametros.put("totalletra", letra);
            parametros.put("totaliva", vista.txtliquidaciontotal.getText());
            parametros.put("total", vista.txttotalfactura.getText());
            JasperPrint jp = JasperFillManager.fillReport(RUTALOCAL + "/src/informes/factura.jasper", parametros, conn);
            JasperViewer view = new JasperViewer(jp, false);
            view.setTitle("FACTURA");
            view.setExtendedState(Frame.MAXIMIZED_BOTH);

            view.setVisible(true);
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(menuprincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(menuprincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarbuscadorfactura() {
        vista.jDialog2.setSize(779, 462);
        vista.jDialog2.setLocationRelativeTo(null);
        vista.jDialog2.setVisible(true);
        vista.txtinicio.setText("");
        vista.txtfin.setText("");
        vista.txtinicio.requestFocus();
        URL url = getClass().getResource("/imagenes/healthcare.png");
        ImageIcon img = new ImageIcon(url);
        vista.setIconImage(img.getImage());

    }

    public void cargartablafactura(String fecha1, String fecha2) {
        String sql = "select * from facturaventa fv inner join cliente c on fv.idcliente=c.idcliente ";
        sql += " where date(fac_fecha) BETWEEN '" + fecha1 + "' and '" + fecha2 + "' ";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelobuscafactura.setRowCount(0);
            while (rs.next()) {
                modelobuscafactura.addRow(new Object[]{
                    rs.getString("idfactura"),
                    rs.getString("fac_nro"),
                    rs.getString("fac_fecha"),
                    rs.getString("fac_condicion"),
                    rs.getString("fac_estado"),
                    rs.getString("idcliente"),
                    rs.getString("cli_nombre"),
                    rs.getString("cli_apellido"),
                    rs.getString("cli_ci")
                });
            }
            vista.tablafactura.setModel(modelobuscafactura);
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void transferirfactura() {
        int fila = vista.tablafactura.getSelectedRow();

        String cod, nrofac, fech, condicion_, estado_, cliente, Nombre, Apellido, Ruc;

        cod = vista.tablafactura.getValueAt(fila, 0).toString();
        nrofac = vista.tablafactura.getValueAt(fila, 1).toString();
        fech = vista.tablafactura.getValueAt(fila, 2).toString();
        condicion_ = vista.tablafactura.getValueAt(fila, 3).toString();
        estado_ = vista.tablafactura.getValueAt(fila, 4).toString();
        cliente = vista.tablafactura.getValueAt(fila, 5).toString();
        Nombre = vista.tablafactura.getValueAt(fila, 6).toString();
        Apellido = vista.tablafactura.getValueAt(fila, 7).toString();
        Ruc = vista.tablafactura.getValueAt(fila, 8).toString();

        vista.txtid.setText(cod);
        vista.txtnumero.setText(nrofac);
        vista.txtfecha.setText(fech);
        if (condicion_.equals("CONTADO")) {
            vista.optcontado.setSelected(true);

        } else {
            vista.optcredito.setSelected(true);
        }
        vista.txtestado.setText(estado_);
        vista.txtcliente.setText(cliente);
        vista.txtnombre.setText(Nombre);
        vista.txtapellido.setText(Apellido);
        vista.txtruc.setText(Ruc);
        //cargar la tabla detalle
        modelodetalle.setRowCount(0);
        String sql = "select * from detallefactura d inner join producto p on d.idproducto=p.idproducto";
        sql += " where d.idfactura='" + vista.txtid.getText() + "'";

        try {
            st = conexion.conexion.sta(st);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("p.pro_iva").equals("0")) {
                    modelodetalle.addRow(new Object[]{
                        rs.getString("idproducto"),
                        rs.getString("pro_nombre"),
                        rs.getString("d.fac_cantidad"),
                        rs.getString("d.fec_precio"),
                        Integer.toString(rs.getInt("d.cantidad") * rs.getInt("d.fec_precio")),
                        "",
                        ""
                    });

                }
                if (rs.getString("p.pro_iva").equals("5")) {
                    modelodetalle.addRow(new Object[]{
                        rs.getString("idproducto"),
                        rs.getString("pro_nombre"),
                        rs.getString("d.fac_cantidad"),
                        rs.getString("d.fec_precio"),
                        "",
                        Integer.toString(rs.getInt("d.fac_cantidad") * rs.getInt("d.fec_precio")),
                        ""
                    });

                }
                if (rs.getString("p.pro_iva").equals("10")) {
                    modelodetalle.addRow(new Object[]{
                        rs.getString("idproducto"),
                        rs.getString("pro_nombre"),
                        rs.getString("d.fac_cantidad"),
                        rs.getString("d.fec_precio"),
                        "",
                        "",
                        Integer.toString(rs.getInt("d.fac_cantidad") * rs.getInt("d.fec_precio"))
                    });

                }
            }
            vista.tabladetalle.setModel(modelodetalle);
            st.close();
            rs.close();
            total();

            vista.jDialog2.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void aumentarstock(String idproducto, String cantidad) {
        // int cantidadfilas=vista.tabladetalle.getRowCount();
        String sql = "update producto set pro_cantidad=pro_cantidad+'" + cantidad + "' where idproducto='" + idproducto + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descontarstock(String idproducto, String cantidad) {
        String sql = "update producto set pro_cantidad=pro_cantidad-'" + cantidad + "' where idproducto='" + idproducto + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void anularfactura() {
        String sql = "update facturaventa set fac_estado='ANULADO' where idfactura='" + idfactura + "'";

        try {
            st = conexion.conexion.sta(st);
            st.executeUpdate(sql);
        st.close();
            JOptionPane.showMessageDialog(null, "FACTURA ANULADA");
        } catch (SQLException ex) {
            Logger.getLogger(facturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Clear_table() {
        for (int i = 0; i < vista.tabladetalle.getRowCount(); i++) {
            modelodetalle.removeRow(i);
            i -= 1;
        }
    }

}
