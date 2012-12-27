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
public class CarControlsPanel extends JPanel {
    private JLabel title = new JLabel("Car controls");

    private JButton nextButton = new JButton("Next Car");
    private JButton previousButton = new JButton("Previous Car");

    private JLabel speedLabel = new JLabel("Speed:");
    //TODO get load default values from somewhere to both model and view
    private JTextField speedTextField = new JTextField();
    private JButton speedApplyButton = new JButton("Apply speed");

    public CarControlsPanel(Dimension preferredSize){
        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(nextButton);
        this.add(previousButton);
        this.add(speedLabel);
        this.add(speedTextField);
        this.add(speedApplyButton);

        BoxLayout layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutManager);

        this.setPreferredSize(preferredSize);

        JLabel emptyLabel = new JLabel();
        int preferredHeight = this.getPreferredSize().height;
        Component[] components = this.getComponents();
        for(Component component:components){
            preferredHeight-=component.getPreferredSize().height;
        }
        emptyLabel.setPreferredSize(new Dimension(1, preferredHeight));
        this.add(emptyLabel);
    }
}
