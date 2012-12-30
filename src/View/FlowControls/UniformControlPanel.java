package View.FlowControls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.carflow.UniformCarFlow;
import View.Messages;
import View.Utility.EmptyLabel;
import View.Utility.Localizator;
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
public class UniformControlPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Uniform flow controls");
    private JLabel param_T_label = new JLabel("T:");
    private JTextField param_T_TextField = new JTextField();
    private JButton applyButton = new JButton("Apply");
    private TunnelController controller;
    private Engine engine;

    public UniformControlPanel(Dimension preferredSize, final TunnelController controller, final Localizator localizator){
        this.controller=controller;
        this.engine=controller.getEngine();
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(param_T_label);
        this.add(param_T_TextField);
        this.add(applyButton);

        localizator.addLocalizable(title, Messages.UniformTitle);
        localizator.addLocalizable(param_T_label, Messages.UniformT);
        localizator.addLocalizable(applyButton, Messages.UniformApply);

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
                controller.changeUniformT(param_T_TextField.getText());
            }
        });

        param_T_TextField.addKeyListener(new NumberKeyListener(
                UniformCarFlow.MINIMUM_PARAMETER_T,
                UniformCarFlow.MAXIMUM_PARAMETER_T
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
        param_T_TextField.setText(engine.getUniformCarFlow().getParam_T()+"");
        if (engine.getCarFlow() instanceof UniformCarFlow){
            this.setVisible(true);
        }
        else{
            this.setVisible(false);
        }

    }
}
