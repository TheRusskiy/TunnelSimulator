package View.Controls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.Road;
import View.NumberKeyListener;

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
public class RoadPropertiesControlPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Road Properties");

    private JLabel roadLengthLabel = new JLabel("Road length:");
    private JTextField roadLengthTextField = new JTextField();

    private JLabel zoomLabel = new JLabel("zoom:");
    private JTextField zoomTextField = new JTextField();

    private JButton applyButton = new JButton("Apply ");
    private JLabel emptyLabel = new JLabel();
    private TunnelController controller;
    private Engine engine;

    public RoadPropertiesControlPanel(Dimension preferredSize, final TunnelController controller){
        this.controller=controller;
        this.engine=controller.getEngine();
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(roadLengthLabel);
        this.add(roadLengthTextField);
        this.add(zoomLabel);
        this.add(zoomTextField);
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
               controller.changeRoadLengthAndScale(roadLengthTextField.getText(), zoomTextField.getText());
            }
        });

        roadLengthTextField.addKeyListener(new NumberKeyListener(
                Road.MINIMUM_LENGTH,
                Road.MAXIMUM_LENGTH
        ));
        zoomTextField.addKeyListener(new NumberKeyListener(
                VisualPanel.MINIMUM_SCALE,
                VisualPanel.MAXIMUM_SCALE
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
          roadLengthTextField.setText(engine.getRoad().getRoadLength()+"");
          zoomTextField.setText(controller.getScale() + "");
    }
}
