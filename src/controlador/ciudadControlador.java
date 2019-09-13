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
import modelo.ciudadModelo;
import vista.ciudadVista;

/**
 *
 * @author Santa206
 */
public class ciudadControlador implements ActionListener, KeyListener, MouseListener {

    ciudadModelo modelo;
    ciudadVista vista;

    public String operacion = "";

    public ciudadControlador(ciudadModelo modelo, ciudadVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnnuevo.addActionListener(this);
        vista.btnguardar.addActionListener(this);
        vista.btnmodificar.addActionListener(this);
        vista.btncancelar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btnaceptar.addActionListener(this);
        vista.txtbuscar.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();

        if (p.equals(vista.btnaceptar)) {
            modelo.transferirCiudad();
            vista.jDialog1.dispose();

            modelo.activabotones(false, false, true, true, true, true);
        }
        if (p.equals(vista.btnbuscar)) {
            modelo.mostrarbuscador();
            modelo.cargarTablaFiltro("");
        }
        if (p.equals(vista.btneliminar)) {
            int r = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR?", "ATENCIÓN", 0);
            if (r == 0) {
                modelo.setCodigo(vista.txtcodigo.getText());
                modelo.eliminar();
                modelo.limpiar();
                modelo.activabotones(true, false, false, false, true, true);
            }

        }
        if (p.equals(vista.btncancelar)) {
            modelo.limpiar();
            modelo.activabotones(true, false, false, false, true, true);
        }

        if (p.equals(vista.btnnuevo)) {
            vista.txtdescripcion.setEnabled(true);
            modelo.generarcodigo();
            modelo.activabotones(false, true, false, false, false, true);
            vista.txtdescripcion.requestFocus();
        }
        if (p.equals(vista.btnmodificar)) {
            operacion = "MODIFICAR";
            modelo.activabotones(false, true, false, false, false, true);
            vista.txtdescripcion.setEnabled(true);
            vista.txtcodigo.setEnabled(false);
        }
        if (p.equals(vista.btnguardar)) {
            if (operacion.equals("MODIFICAR")) {
                if (vista.txtdescripcion.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO DESCRIPCIÓN ESTA VACIO");
                    vista.txtdescripcion.requestFocus();

                } else {
                    int r = JOptionPane.showConfirmDialog(null, "DESEA MODIFICAR?", "ATENCIÓN", 0);
                    if (r == 0) {
                        modelo.setCodigo(vista.txtcodigo.getText());
                        modelo.setDescripcion(vista.txtdescripcion.getText());
                        modelo.modificar();
                        modelo.limpiar();
                        modelo.activabotones(true, false, false, false, true, true);
                        operacion = "";
                    }
                }
            } else {
                if (vista.txtdescripcion.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO DESCRIPCIÓN ESTA VACIO");
                    vista.txtdescripcion.requestFocus();

                } else {
                    int r = JOptionPane.showConfirmDialog(null, "DESEA GUARDAR?", "ATENCIÓN", 0);
                    if (r == 0) {
                        modelo.setCodigo(vista.txtcodigo.getText());
                        modelo.setDescripcion(vista.txtdescripcion.getText());
                        modelo.insertar();
                        modelo.limpiar();
                        modelo.activabotones(true, false, false, false, true, true);
                        operacion = "";
                    }
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.txtbuscar)) {
            modelo.cargarTablaFiltro(vista.txtbuscar.getText());

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.txtbuscar)) {
            modelo.cargarTablaFiltro(vista.txtbuscar.getText());

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.btnaceptar)) {
            modelo.transferirCiudad();
            modelo.limpiar();
            modelo.activabotones(false, false, true, true, true, true);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
