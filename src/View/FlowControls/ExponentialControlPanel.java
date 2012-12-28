package View.FlowControls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.carflow.ExponentialCarFlow;
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
public class ExponentialControlPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Uniform flow controls");
    private JLabel param_T_label = new JLabel("T:");
    private JLabel param_lambda_label = new JLabel("Lambda:");
    private JTextField param_T_TextField = new JTextField();
    private JButton applyButton = new JButton("Apply");
    private TunnelController controller;
    private Engine engine;

    public ExponentialControlPanel(Dimension preferredSize, final TunnelController controller){
        this.controller=controller;
        this.engine=controller.getEngine();
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(param_lambda_label);
        this.add(param_T_label);
        this.add(param_T_TextField);
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
                controller.changeExponentialT(param_T_TextField.getText());
            }
        });

        param_T_TextField.addKeyListener(new NumberKeyListener(
                ExponentialCarFlow.MINIMUM_PARAMETER_T,
                ExponentialCarFlow.MAXIMUM_PARAMETER_T
        ));
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
        param_T_TextField.setText(engine.getExponentialCarFlow().getParam_T()+"");
        param_lambda_label.setText("Lambda = "+engine.getExponentialCarFlow().getParam_lambda());
        if (engine.getCarFlow() instanceof ExponentialCarFlow){
            this.setVisible(true);
        }
        else{
            this.setVisible(false);
        }
    }
}
