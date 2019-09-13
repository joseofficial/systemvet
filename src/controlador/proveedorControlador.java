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
import modelo.proveedorModelo;
import vista.proveedorVista;

/**
 *
 * @author Santa206
 */
public class proveedorControlador implements ActionListener, MouseListener, KeyListener {

    proveedorModelo modelo;
    proveedorVista vista;
    public String operacion = "";

    public proveedorControlador(proveedorModelo modelo, proveedorVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnaceptar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btncancelar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnguardar.addActionListener(this);
        vista.btnmodificar.addActionListener(this);
        vista.btnnuevo.addActionListener(this);
        vista.txtnombre.addActionListener(this);
        vista.txtnombre.addKeyListener(this);
        vista.txtruc.addActionListener(this);
        vista.txtruc.addKeyListener(this);
        vista.txtdireccion.addActionListener(this);
        vista.txtdireccion.addKeyListener(this);
        vista.txttelefono.addActionListener(this);
        vista.txttelefono.addKeyListener(this);
        vista.txtcorreo.addActionListener(this);
        vista.txtcorreo.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();

        if (p.equals(vista.txtnombre)) {
            vista.txtruc.requestFocus();
        }
        if (p.equals(vista.txtruc)) {
            vista.txtdireccion.requestFocus();
        }
        if (p.equals(vista.txtdireccion)) {
            vista.txttelefono.requestFocus();
        }
        if (p.equals(vista.txttelefono)) {
            vista.txtcorreo.requestFocus();

        }
        if (p.equals(vista.txtcorreo)) {
            vista.btnguardar.requestFocus();
        }

        if (p.equals(vista.btnnuevo)) {
            modelo.desbloqueardatos();
            modelo.generarcodigo();
            modelo.activaBotones(false, true, false, false, true, true);
            vista.txtnombre.requestFocus();
        }
        if (p.equals(vista.btnguardar)) {
            if (operacion.equals("MODIFICAR")) {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE ESTA VACIO");
                    vista.txtnombre.requestFocus();

                } else {
                    if (vista.txtruc.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "EL CAMPO RUC ESTA VACIO");
                        vista.txtruc.requestFocus();

                    } else {
                        int r = JOptionPane.showConfirmDialog(null, "DESEA MODIFICAR?", "PREGUNTA", 0);

                        if (r == 0) {
                            modelo.setCodigo(vista.txtcodigo.getText());
                            modelo.setNombre(vista.txtnombre.getText());
                            modelo.setRuc(vista.txtruc.getText());
                            modelo.setDireccion(vista.txtdireccion.getText());
                            modelo.setTelefono(vista.txttelefono.getText());
                            modelo.setCorreo(vista.txtcorreo.getText());

                            modelo.modificar();
                            modelo.limpiar();
                            modelo.bloqueardatos();
                            modelo.activaBotones(true, false, false, false, true, true);
                            operacion = "";

                        }
                    }
                }

            } else {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE ESTA VACIO");
                    vista.txtnombre.requestFocus();

                }
                if (vista.txtruc.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO RUC ESTA VACIO");
                    vista.txtruc.requestFocus();

                } else {
                    int r = JOptionPane.showConfirmDialog(null, "DESEA GUARDAR?", "PREGUNTA", 0);

                    if (r == 0) {
                        modelo.setCodigo(vista.txtcodigo.getText());
                        modelo.setNombre(vista.txtnombre.getText());
                        modelo.setRuc(vista.txtruc.getText());
                        modelo.setDireccion(vista.txtdireccion.getText());
                        modelo.setTelefono(vista.txttelefono.getText());
                        modelo.setCorreo(vista.txtcorreo.getText());

                        modelo.insertar();
                        modelo.limpiar();
                        modelo.bloqueardatos();
                        modelo.activaBotones(true, false, false, false, true, true);
                        operacion = "";

                    }
                }
            }
        }

        if (p.equals(vista.btnmodificar)) {
            modelo.desbloqueardatos();
            vista.txtnombre.requestFocus();
            modelo.activaBotones(false, true, false, false, false, true);
            operacion = "MODIFICAR";

        }
        if (p.equals(vista.btneliminar)) {
            int r = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR?", "PREGUNTA", 0);
            if (r == 0) {
                modelo.setCodigo(vista.txtcodigo.getText());
                modelo.eliminar();
                modelo.limpiar();
                modelo.bloqueardatos();
                modelo.activaBotones(true, false, false, false, true, true);

            }
        }
        if (p.equals(vista.btncancelar)) {
            modelo.limpiar();
            modelo.bloqueardatos();
            modelo.activaBotones(true, false, false, false, true, true);

        }
        if (p.equals(vista.btnbuscar)) {
            modelo.mostrarbuscador();
            modelo.cargatablafiltro("");

        }
        if (p.equals(vista.btnaceptar)) {
            modelo.transferirproveedor();
            vista.jDialog1.dispose();
            modelo.bloqueardatos();
            modelo.activaBotones(false, false, true, true, false, true);

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object p=e.getSource();
      
        if (p.equals(vista.tablafiltro)) {
            modelo.transferirproveedor();
            modelo.bloqueardatos();
            modelo.activaBotones(false, false, true, true, true, true);
        }
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

    @Override
    public void keyTyped(KeyEvent e) {
        Object p = e.getSource();
        if (vista.txtnombre.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isDigit(c)) {
                //getToolkit().beep();
                e.consume();
            }
        }

        if (vista.txtruc.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                e.consume();

            }

        }
        if (vista.txttelefono.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isLetter( c)) {
                //getToolkit().beep();
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object p= e.getSource();
        
        if (p.equals(vista.txtbuscar)) { 
            modelo.cargatablafiltro(vista.txtbuscar.getText());
        }
    }

}
