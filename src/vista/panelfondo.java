package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 *
 * @author john
 */
public class panelfondo extends javax.swing.JPanel {

    public panelfondo() {
        initComponents();
        this.setSize(537, 360);
        
    }

    public void paintComponent(Graphics g) {
        Dimension tam = getSize();
        ImageIcon ImagenFondo = new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/fondo_ref.png")).getImage());
        
        g.drawImage(ImagenFondo.getImage(), 0, 0, tam.width, tam.height, null);
        setOpaque(false);
        this.setLocation(100, 110);
        this.setBackground(Color.WHITE);
        
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
