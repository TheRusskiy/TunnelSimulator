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
public class TimeControlsPanel extends JPanel {
    private JLabel title = new JLabel("Time Controls");
    private JLabel autoDelayLabel = new JLabel("Auto Delay:");
    //TODO get load default values from somewhere to both model and view
    private JTextField autoDelayTextField = new JTextField();
    private JButton autoDelayApplyButton = new JButton("Apply auto delay");
    private JButton nextStepButton = new JButton("Next Step");
    private JLabel autoLabel = new JLabel("Auto simulation:");
    private JButton autoDelayOnButton = new JButton("On");
    private JButton autoDelayOffButton = new JButton("Off");

    public TimeControlsPanel(Dimension preferredSize){
        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(autoDelayLabel);
        this.add(autoDelayTextField);
        this.add(autoDelayApplyButton);
        this.add(nextStepButton);
        this.add(autoLabel);
        this.add(autoDelayOnButton);
        this.add(autoDelayOffButton);


        BoxLayout layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutManager);
        //this.setPreferredSize(preferredSize);
        //this.setMinimumSize(preferredSize);
        //this.setMaximumSize(preferredSize);
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
