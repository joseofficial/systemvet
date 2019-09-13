/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.loginModelo;
import vista.login;

/**
 *
 * @author Santa206
 */
public class loginControlador implements ActionListener {

    login vista;
    loginModelo modelo;

    public loginControlador(login vista, loginModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        vista.btningresar.addActionListener(this);
        vista.txtusuario.addActionListener(this);
        vista.txtclave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.txtusuario)) {
            vista.txtclave.requestFocus();
        }
        if (p.equals(vista.txtclave)) {
            vista.btningresar.doClick();
        }

        if (p.equals(vista.btningresar)) {
            if (vista.txtusuario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(vista, "EL CAMPO USUARIO ESTA VACIO");
                vista.txtusuario.requestFocus();
            } else {
                if (vista.txtclave.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(vista, "EL CAMPO CONTRASEÑA ESTA VACIO");

                } else {
                    
                    modelo.acceder();
                  
                }
            }

        }

    }

}
