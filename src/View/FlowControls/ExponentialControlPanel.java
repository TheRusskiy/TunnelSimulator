package View.FlowControls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.carflow.ExponentialCarFlow;
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
public class ExponentialControlPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Exponential flow controls");
    private JLabel param_T_label = new JLabel("T:");
    private JLabel param_lambda_label = new JLabel("Lambda:");
    private JLabel param_lambda_value_label = new JLabel("iokjofdho");
    private JTextField param_T_TextField = new JTextField();
    private JButton applyButton = new JButton("Apply");
    private TunnelController controller;
    private Engine engine;

    public ExponentialControlPanel(Dimension preferredSize, final TunnelController controller, final Localizator localizator){
        this.controller=controller;
        this.engine=controller.getEngine();
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(param_lambda_label);
        this.add(param_lambda_value_label);
        this.add(param_T_label);
        this.add(param_T_TextField);
        this.add(applyButton);

        localizator.addLocalizable(title, Messages.ExponentialTitle);
        localizator.addLocalizable(param_T_label, Messages.ExponentialT);
        localizator.addLocalizable(param_lambda_label, Messages.ExponentialLambda);
        localizator.addLocalizable(applyButton, Messages.ExponentialApply);


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

        NumberKeyFilter.addFilterTo(param_T_TextField, ExponentialCarFlow.MINIMUM_PARAMETER_T,
                ExponentialCarFlow.MAXIMUM_PARAMETER_T);
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
        param_lambda_value_label.setText("="+engine.getExponentialCarFlow().getParam_lambda()+"");
        if (engine.getCarFlow() instanceof ExponentialCarFlow){
            this.setVisible(true);
        }
        else{
            this.setVisible(false);
        }
    }
    @Override
    public void notifyOfCarModelsChange() {
    }
}
