package View;

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
    private CarControlsPanel CarControlsPanel;
    private ModelPropertiesControlPanel modelPropertiesControlPanel;
    private VisualPanel visualPanel;
    private Dimension timeControlsDimension = new Dimension(120, 180);
    private Dimension modelPropertiesDimension = new Dimension(120, 180);
    private Dimension carControlsDimension = new Dimension(120, 180);
    private Dimension visualPanelInsideDimension = new Dimension(600, 600);
    private Dimension visualPanelDimension = new Dimension(200, 200);
    private Container controlPanel;
    private Container parentPanel;


    public TunnelView(){
        frame = new JFrame("Tunnel simulator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        parentPanel=new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        frame.add(parentPanel);

        visualPanel = new VisualPanel(visualPanelInsideDimension);
        JScrollPane scrollPane = new JScrollPane(visualPanel);//Pass inside constructor!
        scrollPane.setPreferredSize(visualPanelDimension);
        parentPanel.add(scrollPane);
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
        controlPanel =new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        parentPanel.add(controlPanel);

        timeControlsPanel = new TimeControlsPanel(timeControlsDimension);
        controlPanel.add(timeControlsPanel);

        modelPropertiesControlPanel = new ModelPropertiesControlPanel(carControlsDimension);
        controlPanel.add(modelPropertiesControlPanel);

        CarControlsPanel = new CarControlsPanel(modelPropertiesDimension);
        controlPanel.add(CarControlsPanel);



        frame.pack();
        frame.setVisible(true);
    }
}
