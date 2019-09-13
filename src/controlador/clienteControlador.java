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
import modelo.clienteModelo;
import vista.clientesVista;
import vista.menuprincipal;

/**
 *
 * @author Santa206
 */
public class clienteControlador implements ActionListener, KeyListener, MouseListener {

    clientesVista vista;
    clienteModelo modelo;

    public String operacion = "";

    public clienteControlador(clientesVista vista, clienteModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        vista.btnnuevo.addActionListener(this);
        vista.btnmodificar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btnguardar.addActionListener(this);
        vista.btncancelar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.btnaceptar.addActionListener(this);
        vista.txtbuscar.addKeyListener(this);
        vista.txtnombre.addKeyListener(this);
        vista.txtapellido.addKeyListener(this);
        vista.txtci.addKeyListener(this);
        vista.txttelefono.addKeyListener(this);
        vista.txtdireccion.addKeyListener(this);
        vista.txtnombre.addActionListener(this);
        vista.txtapellido.addActionListener(this);
        vista.txtci.addActionListener(this);
        vista.txtdireccion.addActionListener(this);
        vista.txttelefono.addActionListener(this);
        vista.btnsalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.btnsalir)) {

            vista.dispose();
            
        }
        if (p.equals(vista.txtnombre)) {
            vista.txtapellido.requestFocus();

        }
        if (p.equals(vista.txtapellido)) {
            vista.txtci.requestFocus();

        }
        if (p.equals(vista.txtci)) {
            vista.txttelefono.requestFocus();
        }
        if (p.equals(vista.txttelefono)) {
            vista.txtdireccion.requestFocus();

        }
        if (p.equals(vista.txtdireccion)) {
            vista.btnguardar.requestFocus();

        }

        if (p.equals(vista.btnnuevo)) {
            modelo.desbloqueardatos();
            modelo.generarcodigo();
            modelo.activaBotones(false, true, false, false, false, true);
            vista.txtnombre.requestFocus();

        }
        if (p.equals(vista.btnguardar)) {
            if (operacion.equals("MODIFICAR")) {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE ESTA VACIO");
                    vista.txtnombre.requestFocus();

                } else {
                    if (vista.txtapellido.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "EL CAMPO APELLIDO ESTA VACIO");
                        vista.txtapellido.requestFocus();
                    } else {
                        if (vista.txtci.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "EL CAMPO CI ESTA VACIO");
                            vista.txtci.requestFocus();

                        } else {
                            int r = JOptionPane.showConfirmDialog(null, "DESEA MODIFICAR?", "PREGUNTA", 0);

                            if (r == 0) {
                                modelo.setCodigo(vista.txtcodigo.getText());
                                modelo.setNombre(vista.txtnombre.getText());
                                modelo.setApellido(vista.txtapellido.getText());
                                modelo.setCi(vista.txtci.getText());
                                modelo.setTelefono(vista.txttelefono.getText());
                                modelo.setDireccion(vista.txtdireccion.getText());
                                modelo.modificar();
                                modelo.limpiar();
                                modelo.bloqueardatos();
                                modelo.activaBotones(true, false, false, false, true, true);
                                operacion = "";

                            }
                        }
                    }
                }

            } else {
                if (vista.txtnombre.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "EL CAMPO NOMBRE ESTA VACIO");
                    vista.txtnombre.requestFocus();

                } else {
                    if (vista.txtapellido.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "EL CAMPO APELLIDO ESTA VACIO");
                        vista.txtapellido.requestFocus();

                    } else {
                        if (vista.txtci.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "EL CAMPO CI ESTA VACIO");
                            vista.txtci.requestFocus();

                        } else {
                            int r = JOptionPane.showConfirmDialog(null, "DESEA GUARDAR?", "PREGUNTA", 0);

                            if (r == 0) {
                                modelo.setCodigo(vista.txtcodigo.getText());
                                modelo.setNombre(vista.txtnombre.getText());
                                modelo.setApellido(vista.txtapellido.getText());
                                modelo.setCi(vista.txtci.getText());
                                modelo.setTelefono(vista.txttelefono.getText());
                                modelo.setDireccion(vista.txtdireccion.getText());

                                modelo.insertar();
                                modelo.limpiar();
                                modelo.bloqueardatos();
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
        if (p.equals(vista.btneliminar)) {
            int r = JOptionPane.showConfirmDialog(null, "DESEA ELIMIAR?", "PREGUNTA", 0);
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
            modelo.cargarTablaFiltro("");
        }
        if (p.equals(vista.btnaceptar)) {
            modelo.transferirCliente();
            vista.jDialog1.dispose();
            modelo.bloqueardatos();
            modelo.activaBotones(false, false, true, true, true, true);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        Object p = e.getSource();
        if (vista.txtcodigo.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                //getToolkit().beep();
                e.consume();
            }
        }
        if (vista.txtnombre.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isDigit(c)) {
                //getToolkit().beep();
                e.consume();
            }
        }
        if (vista.txtapellido.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isDigit(c)) {
                //getToolkit().beep();
                e.consume();
            }
        }
        if (vista.txtci.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                //getToolkit().beep();
                e.consume();
            }
        }
        if (vista.txttelefono.equals(p)) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
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
        Object p = e.getSource();
        if (p.equals(vista.txtbuscar)) {
            modelo.cargarTablaFiltro(vista.txtbuscar.getText());

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.tablafiltro)) {
            modelo.transferirCliente();
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

}
