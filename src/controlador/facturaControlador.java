/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.facturaModelo;
import vista.facturaCabVista;

/**
 *
 * @author Santa206
 */
public class facturaControlador implements ActionListener, KeyListener, MouseListener {

    facturaCabVista vista;
    facturaModelo modelo;

    public facturaControlador(facturaCabVista vista, facturaModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.btnnuevo.addActionListener(this);
        vista.txtnumero.addActionListener(this);
        vista.optcontado.addKeyListener(this);
        vista.optcredito.addKeyListener(this);
        vista.btnbuscarcliente.addActionListener(this);
        vista.btnaceptar.addActionListener(this);
        vista.txtfiltrocliente.addKeyListener(this);
        vista.txtidproducto.addActionListener(this);
        vista.btnadd.addActionListener(this);
        vista.txtcantidad.addActionListener(this);
        vista.txtcantidad.addKeyListener(this);
        vista.txtidproducto.addKeyListener(this);
        vista.btndell.addActionListener(this);
        vista.txtbuscarproducto.addKeyListener(this);
        vista.btnaceptar3.addActionListener(this);
        vista.btnguardar.addActionListener(this);
        vista.btnimprimir.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btnbuscarfactura.addActionListener(this);
        vista.btnaceptar2.addActionListener(this);
        vista.btncancelar.addActionListener(this);
        vista.btnanular.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();

        if (p.equals(vista.btnanular)) {

            if (vista.txtestado.getText().equals("PENDIENTE")) {
                modelo.setIdfactura(vista.txtid.getText());
                modelo.anularfactura();

                for (int i = 0; i < vista.tabladetalle.getRowCount(); i++) {
                    modelo.aumentarstock(vista.tabladetalle.getValueAt(i, 0).toString(), vista.tabladetalle.getValueAt(i, 2).toString());

                }
                vista.txtestado.setText("ANULADO");
                modelo.limpiar();
                modelo.Clear_table();
                modelo.activabotones(false, false, false, true, false, false, true, false, true);
            }else{
                JOptionPane.showMessageDialog(vista, "LA FACTURA NO SE PUEDE ANULAR");
            }
        }
        if (p.equals(vista.btncancelar)) {
            modelo.limpiar();
            modelo.Clear_table();
            modelo.bloquearObjetos();
            modelo.activabotones(false, false, false, true, false, false, true, false, true);

        }
        if (p.equals(vista.btnaceptar2)) {
            modelo.transferirfactura();
            vista.jDialog2.dispose();
            modelo.bloquearObjetos();
            modelo.activabotones(false, false, false, false, false, true, true, true, true);

        }
        if (p.equals(vista.btnbuscarfactura)) {
            modelo.cargartablafactura(vista.txtinicio.getText(), vista.txtfin.getText());

        }

        if (p.equals(vista.btnbuscar)) {
            modelo.mostrarbuscadorfactura();

        }

        if (p.equals(vista.btnimprimir)) {
            modelo.llamarinforme();

        }

        if (p.equals(vista.btnguardar)) {
            modelo.setIdfactura(vista.txtid.getText());
            modelo.setNrofactura(vista.txtnumero.getText());
            modelo.setFecha(vista.txtfecha.getText());
            if (vista.optcontado.isSelected() == true) {
                modelo.setCondicion("CONTADO");

            } else {
                modelo.setCondicion("CREDITO");
            }
            modelo.setEstado(vista.txtestado.getText());
            modelo.setIdcliente(vista.txtcliente.getText());
            modelo.setIdpersonal(vista.txtidpersonal.getText());
            modelo.guardarfactura();

            for (int i = 0; i < vista.tabladetalle.getRowCount(); i++) {
                modelo.detallefactura(vista.tabladetalle.getValueAt(i, 0).toString(),
                        vista.tabladetalle.getValueAt(i, 2).toString(),
                        vista.tabladetalle.getValueAt(i, 3).toString());
                modelo.descontarstock(vista.tabladetalle.getValueAt(i, 0).toString(), vista.tabladetalle.getValueAt(i, 2).toString());
            }
            JOptionPane.showMessageDialog(null, "FACTURA REGISTRADA");
            modelo.bloquearObjetos();
            modelo.activabotones(false, false, false, false, false, true, false, false, true);
        }
        if (p.equals(vista.btnaceptar3)) {
            modelo.transferirdebuscadorproducto();

        }
        if (p.equals(vista.btndell)) {
            modelo.quitar();

        }
        if (p.equals(vista.btnadd)) {
            modelo.añadir(vista.txtidproducto.getText());
            modelo.total();
            modelo.limpiarproducto();

        }
        if (p.equals(vista.txtcantidad)) {
            vista.btnadd.requestFocus();
        }

        if (p.equals(vista.txtnumero)) {
            vista.optcontado.requestFocus();

        }
        if (p.equals(vista.txtidproducto)) {

            if (vista.txtidproducto.getText().equals("")) {
                modelo.mostrarbuscadorproducto();
                modelo.cargartablabuscadorproducto("");
            } else if (vista.txtidproducto.getText().equals("+")) {
                vista.btnguardar.requestFocus();

            } else {
                modelo.cargarproductoporcodigo(vista.txtidproducto.getText());
            }
        }

        if (p.equals(vista.btnnuevo)) {
            modelo.desbloquearObjetos();
            modelo.generarCodigo();
            modelo.activabotones(true, true, true, false, true, false, false, false, true);
            vista.txtnumero.requestFocus();
        }
        if (p.equals(vista.btnbuscarcliente)) {
            modelo.mostrarcliente();
            modelo.cargarclientefiltro("");

        }
        if (p.equals(vista.btnaceptar)) {
            modelo.transferircliente();
            vista.jDialog1.dispose();
            vista.txtidproducto.requestFocus();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Object p = e.getSource();

        if (p.equals(vista.optcontado)) {
            vista.optcredito.requestFocus();
        }
        if (p.equals(vista.optcredito)) {
            vista.btnbuscarcliente.requestFocus();

        }
        if (vista.txtcantidad.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                //getToolkit().beep();
                e.consume();
            }

        }
        if (vista.txtidproducto.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                e.consume();

            }

        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object p = e.getSource();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object p = e.getSource();

        if (p.equals(vista.txtfiltrocliente)) {
            modelo.cargarclientefiltro(vista.txtfiltrocliente.getText());
        }

        if (p.equals(vista.txtbuscarproducto)) {
            modelo.cargartablabuscadorproducto(vista.txtbuscarproducto.getText());

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object p = e.getSource();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
