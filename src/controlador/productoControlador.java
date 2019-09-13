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
import modelo.productoModelo;
import vista.productoVista;

/**
 *
 * @author Santa206
 */
public class productoControlador implements ActionListener, KeyListener, MouseListener {

    productoModelo modelo;
    productoVista vista;
    public String operacion = "";

    public productoControlador(productoModelo modelo, productoVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnnuevo.addActionListener(this);
        vista.btnmodificar.addActionListener(this);
        vista.btnguardar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btnaceptar4.addActionListener(this);
        vista.txtbuscarproducto.addKeyListener(this);
        vista.btncancelar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.btncancelar)) {
            modelo.limpiar();
            modelo.bloqueardatos();
            modelo.activaBotones(true, false, false, false, true, true);
        }
        if (p.equals(vista.btnaceptar4)) {
            modelo.transferirproducto();
            vista.buscadorproducto.setVisible(false);
        }
        if (p.equals(vista.btnbuscar)) {
            modelo.mostrarbuscador();
            modelo.filtroproducto("");

        }

        if (p.equals(vista.btnnuevo)) {
            modelo.desbloqueardatos();
            modelo.generarcodigo();
           
            modelo.activaBotones(false, true, false, false, false, true);
            vista.txtnombre.setText("");
            vista.txtnombre.requestFocus();
        }

        if (p.equals(vista.btnguardar)) {
            if (operacion.equals("MODIFICAR")) {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE NO PUEDE ESTAR VACIO");
                    vista.txtnombre.requestFocus();

                } else {
                    if (vista.txtprecio.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO NO PUEDE ESTAR VACIO");
                        vista.txtprecio.requestFocus();

                    } else {
                        if (vista.txtcantidad.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "EL CAMPO CANTIDAD NO PUEDE ESTAR VACIO");
                            vista.txtcantidad.requestFocus();
                        } else {
                            if (vista.txtiva.getText().trim().length() == 0) {
                                JOptionPane.showMessageDialog(null, "EL CAMPO IVA NO PUEDE ESTAR VACIO");
                                vista.txtiva.requestFocus();
                            } else {
                                int r = JOptionPane.showConfirmDialog(null, "DESEA MODIFICAR?", "PREGUNTA", 0);

                                if (r == 0) {
                                    modelo.setCodigo(vista.txtcodigo.getText());
                                    modelo.setNombre(vista.txtnombre.getText());
                                    modelo.setPrecio(vista.txtprecio.getText());
                                    modelo.setCantidad(vista.txtcantidad.getText());
                                    modelo.setIva(vista.txtiva.getText());
                                    modelo.setProveedor(vista.cbmproveedor.getSelectedIndex());
                                    modelo.modificar();
                                    modelo.limpiar();
                                    modelo.activaBotones(true, false, false, false, true, true);
                                    operacion = "";
                                }

                            }

                        }
                    }
                }

            } else {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE NO PUEDE ESTAR VACIO");
                    vista.txtnombre.requestFocus();
                }
                if (vista.txtprecio.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO PRECIO NO PUEDE ESTAR VACIO");
                    vista.txtprecio.requestFocus();

                } else {
                    if (vista.txtcantidad.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "EL CAMPO CANTIDAD NO PUEDE ESTAR VACIO");
                        vista.txtcantidad.requestFocus();
                    } else {
                        if (vista.txtiva.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "EL CAMPO IVA NO PUEDE ESTAR VACIO");
                            vista.txtiva.requestFocus();
                        } else {
                            int r = JOptionPane.showConfirmDialog(null, "DESEA GUARDAR?", "PREGUNTA", 0);

                            if (r == 0) {
                                modelo.setCodigo(vista.txtcodigo.getText());
                                modelo.setNombre(vista.txtnombre.getText());
                                modelo.setPrecio(vista.txtprecio.getText());
                                modelo.setCantidad(vista.txtcantidad.getText());
                                modelo.setIva(vista.txtiva.getText());
                                modelo.setProveedor(vista.cbmproveedor.getSelectedIndex());
                                modelo.insertar();
                                modelo.limpiar();
                                modelo.activaBotones(true, false, false, false, true, true);
                                operacion = "";

                            }
                        }
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

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.txtbuscarproducto)) {
            modelo.filtroproducto(vista.txtbuscarproducto.getText());

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.btnaceptar4)) {
            modelo.transferirproducto();
            vista.buscadorproducto.setVisible(false);
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

}
