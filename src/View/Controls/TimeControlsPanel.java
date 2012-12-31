package View.Controls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.TimeThread;
import View.Messages;
import View.Utility.EmptyLabel;
import View.Utility.Localizator;
import View.Utility.NumberKeyFilter;

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
public class TimeControlsPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Time Controls");
    private JLabel autoDelayLabel = new JLabel("Auto Delay(ms):");
    private JTextField autoDelayTextField = new JTextField();
    private JButton autoDelayApplyButton = new JButton("Apply");
    private JButton nextStepButton = new JButton("Next Step");
//    private JLabel autoLabel = new JLabel("Auto simulation:");
    private JCheckBox autoCheckBox = new JCheckBox("Enable auto simulation");
//    private JButton autoDelayOnButton = new JButton("On");
//    private JButton autoDelayOffButton = new JButton("Off");
    private TunnelController controller;
    private Engine engine;

    public TimeControlsPanel(Dimension preferredSize, final TunnelController controller, final Localizator localizator){
        this.controller=controller;
        this.engine=controller.getEngine();
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(autoDelayLabel);
        this.add(autoDelayTextField);
        this.add(nextStepButton);
//        this.add(autoLabel);
//        this.add(autoDelayOnButton);
//        this.add(autoDelayOffButton);
        this.add(autoCheckBox);
        this.add(autoDelayApplyButton);

        localizator.addLocalizable(title, Messages.TimeControlsTitle);
        localizator.addLocalizable(autoDelayLabel, Messages.AutoDelayLabel);
        localizator.addLocalizable(autoDelayApplyButton, Messages.AutoDelayApply);
        localizator.addLocalizable(nextStepButton, Messages.NextStepButton);
//        localizator.addLocalizable(autoLabel, Messages.AutoSimulationLabel);
//        localizator.addLocalizable(autoDelayOnButton, Messages.AutoOn);
//        localizator.addLocalizable(autoDelayOffButton, Messages.AutoOff);
        localizator.addLocalizable(autoCheckBox, Messages.TimeControlsAutoCheckbox);

        BoxLayout layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutManager);

        this.setPreferredSize(preferredSize);
        this.setSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

        EmptyLabel emptyLabel = new EmptyLabel(this, EmptyLabel.Direction.Y_AXIS);

//        autoDelayOnButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.autoOn();
//            }
//        });
//
//        autoDelayOffButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.autoOff();
//            }
//        });

        nextStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nextStep();
            }
        });

        autoDelayApplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean buffer =autoCheckBox.isSelected();
                controller.changeAutoDelay(autoDelayTextField.getText());
                controller.autoSimulationEnabled(buffer);
            }
        });

        NumberKeyFilter.addFilterTo(autoDelayTextField, TimeThread.MINIMUM_TICK_TIME,
                TimeThread.MAXIMUM_TICK_TIME);
    }

    @Override
    public void notifyOfDataChange() {

    }

    @Override
    public void notifyOfPropertiesChange() {
        this.autoDelayTextField.setText("" + engine.getAutoTickTime());
        this.autoCheckBox.setSelected(engine.isAutoEnabled());
    }

    @Override
    public void notifyOfStructureChange() {

    }

    @Override
    public void notifyOfFlowChange() {
    }
    @Override
    public void notifyOfCarModelsChange() {
    }
}
