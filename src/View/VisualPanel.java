package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 3:26
 * To change this template use File | Settings | File Templates.
 */
public class VisualPanel extends JPanel {
    private Dimension preferredSize;
    public VisualPanel(Dimension preferredSize){
        this.preferredSize=preferredSize;
    }
    public void paint(Graphics g){
        g.drawLine(10,10,150,150); // Draw a line from (10,10) to (150,150)
    }
    public Dimension getPreferredSize() {
        return this.preferredSize;
    }
}
