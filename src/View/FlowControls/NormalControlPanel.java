package View.FlowControls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.carflow.NormalCarFlow;
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
public class NormalControlPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Normal flow controls");
    private JLabel param_M_label = new JLabel("Mean:");
    private JTextField param_M_TextField = new JTextField();
    private JLabel param_D_label = new JLabel("Deviation:");
    private JTextField param_D_TextField = new JTextField();
    private JButton applyButton = new JButton("Apply");
    private TunnelController controller;
    private Engine engine;

    public NormalControlPanel(Dimension preferredSize, final TunnelController controller, final Localizator localizator){
        this.controller=controller;
        this.engine=controller.getEngine();
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(param_M_label);
        this.add(param_M_TextField);
        this.add(param_D_label);
        this.add(param_D_TextField);
        this.add(applyButton);

        localizator.addLocalizable(title, Messages.NormalTitle);
        localizator.addLocalizable(param_M_label, Messages.NormalMean);
        localizator.addLocalizable(param_D_label, Messages.NormalDeviation);
        localizator.addLocalizable(applyButton, Messages.NormalApply);


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
                controller.changeNormalMandD(param_M_TextField.getText(), param_D_TextField.getText());
            }
        });

        NumberKeyFilter.addFilterTo(param_M_TextField, NormalCarFlow.MINIMUM_PARAMETER_M,
                NormalCarFlow.MAXIMUM_PARAMETER_M);
        NumberKeyFilter.addFilterTo(param_D_TextField, NormalCarFlow.MINIMUM_PARAMETER_D,
                NormalCarFlow.MAXIMUM_PARAMETER_D);

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

    @Override
    public void notifyOfFlowChange() {
        param_M_TextField.setText(engine.getNormalCarFlow().getParam_M() + "");
        param_D_TextField.setText(engine.getNormalCarFlow().getParam_D() + "");
        if (engine.getCarFlow() instanceof NormalCarFlow){
            this.setVisible(true);
        }
        else{
            this.setVisible(false);
        }

    }
}
