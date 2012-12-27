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
public class ModelPropertiesControlPanel extends JPanel {
    private JLabel title = new JLabel("Model Properties");

    private JLabel vMaxLabel = new JLabel("Vmax:");
    //TODO get load default values from somewhere to both model and view
    private JTextField vMaxTextField = new JTextField();

    private JLabel stepTimeLabel = new JLabel("Step time:");
    private JTextField stepTimeField = new JTextField();

    private JButton applyButton = new JButton("Apply ");
    private JLabel emptyLabel = new JLabel();

    public ModelPropertiesControlPanel(Dimension preferredSize){
        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(vMaxLabel);
        this.add(vMaxTextField);
        this.add(stepTimeLabel);
        this.add(stepTimeField);
        this.add(applyButton);


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
