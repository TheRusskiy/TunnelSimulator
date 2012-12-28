package View.Controls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.Road;
import View.Utility.EmptyLabel;
import View.Utility.NumberKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 3:26
 * To change this template use File | Settings | File Templates.
 */
public class ModelPropertiesControlPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Model Properties");

    private JLabel vMaxLabel = new JLabel("Vmax in meters:");
    private JTextField vMaxTextField = new JTextField();

    private JLabel stepTimeLabel = new JLabel("Step time(sec):");
    private JTextField stepTimeField = new JTextField();

    private JButton applyButton = new JButton("Apply ");
    private JLabel emptyLabel = new JLabel();
    private TunnelController controller;
    private Engine engine;

    public ModelPropertiesControlPanel(Dimension preferredSize, final TunnelController controller){
        this.controller=controller;
        this.engine=controller.getEngine();
        controller.registerListener(this);

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
        this.setSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

        EmptyLabel emptyLabel = new EmptyLabel(this, EmptyLabel.Direction.Y_AXIS);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeStepTimeAndVMax(stepTimeField.getText(), vMaxTextField.getText());
            }
        });

        vMaxTextField.addKeyListener(new NumberKeyListener(
                Road.MINIMUM_SPEED_LIMIT,
                Road.MAXIMUM_SPEED_LIMIT
        ));
        stepTimeField.addKeyListener(new NumberKeyListener(
                Engine.MINIMUM_STEP_TIME,
                Engine.MAXIMUM_STEP_TIME
        ));
    }

    @Override
    public void notifyOfDataChange() {

    }

    @Override
    public void notifyOfPropertiesChange() {
        this.vMaxTextField.setText(engine.getRoad().getSpeedLimitation()+"");
        this.stepTimeField.setText(engine.getStepTime()+"");
    }

    @Override
    public void notifyOfStructureChange() {

    }

    @Override
    public void notifyOfFlowChange() {
    }
}
