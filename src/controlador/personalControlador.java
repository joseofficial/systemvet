package controlador;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.personalModelo;
import vista.personalVista;

/**
 *
 * @author ALUMNO
 */
public class personalControlador implements ActionListener, KeyListener, MouseListener {

    personalVista vista;
    personalModelo modelo;

    public String operacion = "";

    public personalControlador(personalVista vista, personalModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.btnnuevo.addActionListener(this);
        vista.btnguardar.addActionListener(this);
        vista.tablapersonal.addMouseListener(this);
        vista.btnmodificar.addActionListener(this);
        vista.btneliminar.addActionListener(this);
        vista.btncancelar.addActionListener(this);
        vista.btnbuscar.addActionListener(this);
        vista.txtbuscar.addKeyListener(this);
        vista.btnaceptar.addActionListener(this);
        vista.txtnombre.addActionListener(this);
        vista.txtapellido.addActionListener(this);
        vista.txtci.addActionListener(this);
        vista.txttelefono.addActionListener(this);
        vista.txtdireccion.addActionListener(this);
        vista.txtcodigo.addKeyListener(this);
        vista.txtnombre.addKeyListener(this);
        vista.txtapellido.addKeyListener(this);
        vista.txtci.addKeyListener(this);
        vista.txttelefono.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();

        if (p.equals(vista.txtcodigo)) {
            vista.txtnombre.requestFocus();

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

        if (p.equals(vista.btnaceptar)) {

            modelo.transferirPersonal2();
            vista.jDialog1.dispose();
            modelo.bloqueardatos();
            modelo.activaBotones(false, false, true, true, true, true);

        }

        if (p.equals(vista.btnbuscar)) {
            modelo.mostrarbuscador();
            modelo.cargartablafiltro("");

        }
        if (p.equals(vista.btnnuevo)) {
            modelo.desbloqueardatos();
            modelo.generarcodigo();
            modelo.activaBotones(false, true, false, false, false, true);
            vista.txtnombre.requestFocus();

        }
        if (p.equals(vista.btnguardar)) {
            if (operacion.equals("MODIFICAR")) {
                if (vista.txtcodigo.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "El Campo Codigo esta vacio");
                    vista.txtcodigo.requestFocus();
                } else {
                    if (vista.txtnombre.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "El Campo nombre esta vacio");
                        vista.txtnombre.requestFocus();
                    } else {
                        if (vista.txtapellido.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "El Campo Apellido esta vacio");
                            vista.txtapellido.requestFocus();
                        } else {
                            if (vista.txtci.getText().trim().length() == 0) {
                                JOptionPane.showMessageDialog(null, "El campo CI esta vacio");
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
                                    modelo.cargartabla();
                                    operacion = "";

                                }
                            }
                        }

                    }
                }

//            } else if (operacion.equals("ELIMINAR")) {
//                int r = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR?", "PREGUNTA", 0);
//                if (r == 0) {
//
//                    modelo.setCodigo(vista.txtcodigo.getText());
//                    modelo.setNombre(vista.txtnombre.getText());
//                    modelo.setApellido(vista.txtapellido.getText());
//                    modelo.setCi(vista.txtci.getText());
//                    modelo.setTelefono(vista.txttelefono.getText());
//                    modelo.setDireccion(vista.txtdireccion.getText());
//
//                    modelo.eliminar();
//                    modelo.limpiar();
//                    modelo.bloqueardatos();
//                    modelo.activaBotones(true, false, false, false, true, true);
//                    modelo.cargartabla();
//                    operacion = "";
//
//                }
            } else {
                if (vista.txtcodigo.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "El Campo Codigo esta vacio");
                    vista.txtcodigo.requestFocus();
                } else {
                    if (vista.txtnombre.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "El Campo nombre esta vacio");
                        vista.txtnombre.requestFocus();
                    } else {
                        if (vista.txtapellido.getText().trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "El Campo Apellido esta vacio");
                            vista.txtapellido.requestFocus();
                        } else {
                            if (vista.txtci.getText().trim().length() == 0) {
                                JOptionPane.showMessageDialog(null, "El campo CI esta vacio");
                                vista.txtci.requestFocus();

                            } else {

                                int r = JOptionPane.showConfirmDialog(null, "DESEA GUADAR?", "PREGUNTA", 0);

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
                                    modelo.cargartabla();
                                    operacion = "";
                                }
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
            int r = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR?", "PREGUNTA", 0);
            if (r == 0) {
                modelo.setCodigo(vista.txtcodigo.getText());
                modelo.eliminar();
                modelo.limpiar();
                modelo.bloqueardatos();
                modelo.activaBotones(true, false, false, false, true, true);
                modelo.cargartabla();
                //modelo.activaBotones(false, true, false, false, false, true);
                //operacion = "ELIMINAR";

            }

        }

        if (p.equals(vista.btncancelar)) {

            modelo.limpiar();
            modelo.bloqueardatos();
            modelo.activaBotones(true, false, false, false, true, true);
            modelo.cargartabla();

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
            modelo.cargartablafiltro(vista.txtbuscar.getText());

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.tablapersonal)) {
            modelo.transferirPersonal();
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
