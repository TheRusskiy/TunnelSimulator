package View;

import Controller.ModelListener;
import Controller.TunnelController;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 3:26
 * To change this template use File | Settings | File Templates.
 */
public class VisualPanel extends JPanel implements ModelListener {
    private Dimension preferredSize = new Dimension(100, 100);
    private TunnelController controller;

    public VisualPanel(TunnelController controller){//Dimension preferredSize){
        this.controller=controller;
        controller.registerListener(this);
    }

    @Override
    public void notifyOfChange() {

    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(10,10,150,150); // Draw a line from (10,10) to (150,150)
        //preferredSize = new Dimension(500, 500);
    }
    public Dimension getPreferredSize() {
        return this.preferredSize;
    }

    public void refreshTunnel() {
        this.repaint();
    }
}
