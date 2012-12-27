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
public class RoadPropertiesControlPanel extends JPanel implements ModelListener {
    private JLabel title = new JLabel("Road Properties");

    private JLabel roadLengthLabel = new JLabel("Road length:");
    //TODO get load default values from somewhere to both model and view
    private JTextField roadLengthTextField = new JTextField();

    private JLabel zoomLabel = new JLabel("zoom:");
    private JTextField zoomTimeField = new JTextField();

    private JButton applyButton = new JButton("Apply ");
    private JLabel emptyLabel = new JLabel();
    private TunnelController controller;

    public RoadPropertiesControlPanel(Dimension preferredSize, TunnelController controller){
        this.controller=controller;
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(roadLengthLabel);
        this.add(roadLengthTextField);
        this.add(zoomLabel);
        this.add(zoomTimeField);
        this.add(applyButton);


        BoxLayout layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutManager);

        this.setPreferredSize(preferredSize);
        this.setSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

        EmptyLabel emptyLabel = new EmptyLabel(this, EmptyLabel.Direction.Y_AXIS);

//        applyButton
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
