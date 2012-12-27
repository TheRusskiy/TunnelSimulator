package View;

import Controller.TunnelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 3:20
 * To change this template use File | Settings | File Templates.
 */
public class TunnelView{
    private JFrame frame;
    private TimeControlsPanel timeControlsPanel;
    private CarControlsPanel carControlsPanel;
    private ModelPropertiesControlPanel modelPropertiesControlPanel;
    private VisualPanel visualPanel;
    private Dimension timeControlsDimension = new Dimension(140, 180);
    private Dimension modelPropertiesDimension = new Dimension(140, 180);
    private Dimension carControlsDimension = new Dimension(140, 180);
    private Dimension visualPanelDimension = new Dimension(800, 200);
    private Container controlPanel;
    private Container parentPanel;
    private TunnelController controller;


    public TunnelView(TunnelController controller){
        this.controller = controller;
        frame = new JFrame("Tunnel simulator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        parentPanel=new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        //parentPanel.setPreferredSize(new Dimension(600, 600));
        frame.add(parentPanel);

        visualPanel = new VisualPanel(controller);//visualPanelInsideDimension);
        JScrollPane scrollPane = new JScrollPane(visualPanel);//Pass inside constructor!
        scrollPane.setPreferredSize(visualPanelDimension);
        parentPanel.add(scrollPane);


        controlPanel =new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        parentPanel.add(controlPanel);

        timeControlsPanel = new TimeControlsPanel(timeControlsDimension, controller);
        controlPanel.add(timeControlsPanel);

        modelPropertiesControlPanel = new ModelPropertiesControlPanel(carControlsDimension, controller);
        controlPanel.add(modelPropertiesControlPanel);

        carControlsPanel = new CarControlsPanel(modelPropertiesDimension, controller);
        controlPanel.add(carControlsPanel);

        //EmptyLabel emptyLabel = new EmptyLabel(controlPanel, EmptyLabel.Direction.X_AXIS);

        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                frame.repaint();
            }
        });
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                frame.repaint();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
