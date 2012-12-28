package View;

import Controller.TunnelController;
import View.Controls.*;
import View.FlowControls.DeterminedControlPanel;
import View.FlowControls.ExponentialControlPanel;
import View.FlowControls.NormalControlPanel;
import View.FlowControls.UniformControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 3:20
 * To change this template use File | Settings | File Templates.
 */
public class TunnelView extends JFrame{
    //private JFrame frame;
    private TimeControlsPanel timeControlsPanel;
    private CarControlsPanel carControlsPanel;
    private ModelPropertiesControlPanel modelPropertiesControlPanel;
    private RoadPropertiesControlPanel roadPropertiesControlPanel;
    private UniformControlPanel uniformControlPanel;
    private ExponentialControlPanel exponentialControlPanel;
    private NormalControlPanel normalControlPanel;
    private DeterminedControlPanel determinedControlPanel;
    JScrollPane scrollPane;
    private VisualPanel visualPanel;
    private Dimension timeControlsDimension = new Dimension(140, 180);
    private Dimension modelPropertiesDimension = new Dimension(140, 180);
    private Dimension roadPropertiesDimension = new Dimension(140, 180);
    private Dimension carControlsDimension = new Dimension(140, 180);
    private Dimension uniformControlPanelDimension = new Dimension(140, 180);
    private Dimension exponentialControlPanelDimension = new Dimension(140, 180);
    private Dimension normalControlPanelDimension = new Dimension(140, 180);
    private Dimension determinedControlPanelDimension = new Dimension(140, 180);
    private Dimension visualPanelDimension = new Dimension(800, 200);
    private Container controlPanel;
    private Container parentPanel;
    private TunnelController controller;


    public TunnelView(TunnelController controller){
        super("Tunnel simulator");
        this.controller = controller;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        parentPanel=new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        this.setContentPane(parentPanel);


        visualPanel = new VisualPanel(controller, this);//visualPanelInsideDimension);
        scrollPane = new JScrollPane(visualPanel);//Pass inside constructor!
        updateScrollPane();
        parentPanel.add(scrollPane);

        visualPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //updateScrollPane(0);
            }
        });

        controlPanel =new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        parentPanel.add(controlPanel);

        timeControlsPanel = new TimeControlsPanel(timeControlsDimension, controller);
        controlPanel.add(timeControlsPanel);

        modelPropertiesControlPanel = new ModelPropertiesControlPanel(carControlsDimension, controller);
        controlPanel.add(modelPropertiesControlPanel);

        carControlsPanel = new CarControlsPanel(modelPropertiesDimension, controller);
        controlPanel.add(carControlsPanel);

        roadPropertiesControlPanel = new RoadPropertiesControlPanel(roadPropertiesDimension, controller);
        controlPanel.add(roadPropertiesControlPanel);

        uniformControlPanel = new UniformControlPanel(uniformControlPanelDimension, controller);
        controlPanel.add(uniformControlPanel);

        exponentialControlPanel = new ExponentialControlPanel(exponentialControlPanelDimension, controller);
        controlPanel.add(exponentialControlPanel);

        normalControlPanel = new NormalControlPanel(normalControlPanelDimension, controller);
        controlPanel.add(normalControlPanel);

        determinedControlPanel = new DeterminedControlPanel(determinedControlPanelDimension, controller);
        controlPanel.add(determinedControlPanel);

        TunnelMenu menu = new TunnelMenu(this, controller);
        this.setJMenuBar(menu);

        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                TunnelView.this.repaint();
            }
        });
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                TunnelView.this.repaint();
            }
        });


        updateSize();
        this.setVisible(true);
        controller.askForNotify();
    }

    private void updateScrollPane() {
        Dimension scrollPanePreferredDimension =new Dimension(
                visualPanelDimension.width,
                visualPanel.getHeight()+30
        );
        Dimension scrollPaneMaximumDimension =new Dimension(
                2000,
                visualPanel.getHeight()+30
        );
        scrollPane.setMaximumSize(scrollPaneMaximumDimension);
        scrollPane.setPreferredSize(scrollPanePreferredDimension);
       //scrollPane.setSize(scrollPanePreferredDimension);
    }

    public void updateSize(){
        if (visualPanel!=null)updateScrollPane();
        this.pack();
        Dimension frameMaxDimension = new Dimension(2000, this.getPreferredSize().height);
        Dimension frameMinDimension = new Dimension(1024, this.getPreferredSize().height);
        //this.setTitle(this.getPreferredSize().width+"");
        this.setMinimumSize(frameMinDimension);
        this.setMaximumSize(frameMaxDimension);

    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        Dimension m = getMaximumSize();
        boolean resize = d.width > m.width || d.height > m.height;
        d.width = Math.min(m.width, d.width);
        d.height = Math.min(m.height, d.height);
        if (resize) {
            Point p = getLocation();
            setVisible(false);
            setSize(d);
            setLocation(p);
            setVisible(true);
        }
        super.paint(g);
    }

}
