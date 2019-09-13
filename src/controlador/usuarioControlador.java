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
import javax.swing.JOptionPane;
import modelo.usuarioModelo;
import vista.usuarioVista;

/**
 *
 * @author Santa206
 */
public class usuarioControlador implements ActionListener, KeyListener{

    usuarioVista vista;
    usuarioModelo modelo;
    public String operacion = "";

    public usuarioControlador(usuarioVista vista, usuarioModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        vista.btnnuevo.addActionListener(this);
        vista.btnguardar.addActionListener(this);
        vista.btnmodificar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btnaceptar.addActionListener(this);
        vista.btncancelar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.btncancelar)) {
            modelo.limpiar();
            modelo.bloqueardatos();
            modelo.activabotones(true, false, false, true, true);
            
        }
        if (p.equals(vista.btnaceptar)) {
            modelo.transferirdatos();
            vista.jDialog1.setVisible(false);
            modelo.bloqueardatos();
            modelo.activabotones(false, false, true, false, true);
        }
        if (p.equals(vista.btnbuscar)) {
            modelo.mostrarbuscador();
            modelo.cargarusuarios("");
        }

        if (p.equals(vista.btnnuevo)) {
            modelo.desbloqueardatos();
            modelo.generarcodigo();
            vista.txtnombre.setText("");
            vista.txtnombre.requestFocus();
            modelo.activabotones(false, true, false, false, true);
        }
        if (p.equals(vista.btnguardar)) {
            if (operacion.equals("MODIFICAR")) {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE ESTA VACIO");
                    vista.txtnombre.requestFocus();
                } else {
                    if (vista.txtcontrasena.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "LA CONTRASEÑA NO PUEDE ESTAR VACIA");
                        vista.txtcontrasena.requestFocus();
                    } else {
                        if (vista.optactivo.isSelected() == false && vista.optinactivo.isSelected() == false) {
                            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN ESTADO");

                        } else {
                            if (vista.cmbtipo.getSelectedIndex() == 0) {
                                JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL TIPO DE USUARIO");

                            } else {
                                if (vista.cmbpersonal.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN PERSONAL");
                                } else {
                                    int r = JOptionPane.showConfirmDialog(null, "DESESA MODIFICAR ?", "PREGUNTA", 0);
                                    if (r == 0) {
                                        modelo.setCodigo(vista.txtcodigo.getText());
                                        modelo.setNombre(vista.txtnombre.getText());
                                        modelo.setContrasena(vista.txtcontrasena.getText());
                                        modelo.setTipo(vista.cmbtipo.getSelectedItem().toString());
                                        if (vista.optactivo.isSelected() == true) {
                                            modelo.setEstado("ACTIVO");

                                        } else {
                                            modelo.setEstado("INACTIVO");
                                        }
                                        modelo.setIdpersonal(vista.cmbpersonal.getSelectedIndex());
                                        modelo.modificar();
                                        modelo.limpiar();
                                        modelo.bloqueardatos();
                                        modelo.activabotones(true, false, false, true, true);
                                        operacion = "";

                                    }
                                }
                            }
                        }
                    }

                }
            } else {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE ESTA VACIO");
                    vista.txtnombre.requestFocus();
                } else {
                    if (vista.txtcontrasena.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "LA CONTRASEÑA NO PUEDE ESTAR VACIA");
                        vista.txtcontrasena.requestFocus();
                    } else {
                        if (vista.optactivo.isSelected() == false && vista.optinactivo.isSelected() == false) {
                            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN ESTADO");

                        } else {
                            if (vista.cmbtipo.getSelectedIndex() == 0) {
                                JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL TIPO DE USUARIO");

                            } else {
                                if (vista.cmbpersonal.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN PERSONAL");
                                } else {
                                    int r = JOptionPane.showConfirmDialog(null, "DESEA GUARDAR?", "PREGUNTA", 0);

                                    if (r == 0) {
                                        modelo.setCodigo(vista.txtcodigo.getText());
                                        modelo.setNombre(vista.txtnombre.getText());
                                        modelo.setContrasena(vista.txtcontrasena.getText());
                                        modelo.setTipo(vista.cmbtipo.getSelectedItem().toString());
                                        if (vista.optactivo.isSelected() == true) {
                                            modelo.setEstado("ACTIVO");

                                        } else {
                                            modelo.setEstado("INACTIVO");
                                        }
                                        modelo.setIdpersonal(vista.cmbpersonal.getSelectedIndex());
                                        modelo.insertar();
                                        modelo.limpiar();
                                        modelo.bloqueardatos();
                                        modelo.activabotones(true, false, false, true, true);
                                        operacion = "";
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        if (p.equals(vista.btnmodificar)) {
            operacion = "MODIFICAR";
            modelo.desbloqueardatos();
            vista.txtnombre.requestFocus();
            modelo.activabotones(false, true, false, false, true);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
         Object p = e.getSource();
        if (p.equals(vista.txtbuscarusuario)) {
            modelo.cargarusuarios(vista.txtbuscarusuario.getText());

        }
    }

}
