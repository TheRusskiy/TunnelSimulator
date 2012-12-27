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
public class TimeControlsPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Time Controls");
    private JLabel autoDelayLabel = new JLabel("Auto Delay:");
    //TODO get load default values from somewhere to both model and view
    private JTextField autoDelayTextField = new JTextField();
    private JButton autoDelayApplyButton = new JButton("Apply auto delay");
    private JButton nextStepButton = new JButton("Next Step");
    private JLabel autoLabel = new JLabel("Auto simulation:");
    private JButton autoDelayOnButton = new JButton("On");
    private JButton autoDelayOffButton = new JButton("Off");
    private TunnelController controller;

    public TimeControlsPanel(Dimension preferredSize, TunnelController controller){
        this.controller=controller;
        controller.registerListener(this);

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

        this.setPreferredSize(preferredSize);
        this.setSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

        EmptyLabel emptyLabel = new EmptyLabel(this, EmptyLabel.Direction.Y_AXIS);
    }

    @Override
    public void notifyOfDataChange() {

    }

    @Override
    public void notifyOfPropertiesChange() {

    }

    @Override
    public void notifyOfStructureChange() {

    }
}
