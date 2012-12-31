package View;

import Controller.TunnelController;
import View.Controls.*;
import View.FlowControls.DeterminedControlPanel;
import View.FlowControls.ExponentialControlPanel;
import View.FlowControls.NormalControlPanel;
import View.FlowControls.UniformControlPanel;
import View.Utility.Localizator;

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
    private Dimension timeControlsDimension = new Dimension(180, 180);
    private Dimension modelPropertiesDimension = new Dimension(180, 180);
    private Dimension roadPropertiesDimension = new Dimension(180, 180);
    private Dimension carControlsDimension = new Dimension(180, 180);
    private Dimension uniformControlPanelDimension = new Dimension(180, 180);
    private Dimension exponentialControlPanelDimension = new Dimension(180, 180);
    private Dimension normalControlPanelDimension = new Dimension(180, 180);
    private Dimension determinedControlPanelDimension = new Dimension(180, 180);
    private Dimension visualPanelDimension = new Dimension(800, 200);
    private Container controlPanel;
    private Container parentPanel;
    private TunnelController controller;
    private Color backGroundColor = Color.BLUE;
    private Localizator localizator = new Localizator();


    public TunnelView(TunnelController controller){
        super("Tunnel simulator");
        this.controller = controller;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        parentPanel=new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        this.setContentPane(parentPanel);


        visualPanel = new VisualPanel(controller, this, localizator);//visualPanelInsideDimension);
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

        timeControlsPanel = new TimeControlsPanel(timeControlsDimension, controller, localizator);
        controlPanel.add(timeControlsPanel);

        modelPropertiesControlPanel = new ModelPropertiesControlPanel(carControlsDimension, controller, localizator);
        controlPanel.add(modelPropertiesControlPanel);

        carControlsPanel = new CarControlsPanel(modelPropertiesDimension, controller, localizator);
        controlPanel.add(carControlsPanel);

        roadPropertiesControlPanel = new RoadPropertiesControlPanel(roadPropertiesDimension, controller, localizator);
        controlPanel.add(roadPropertiesControlPanel);

        uniformControlPanel = new UniformControlPanel(uniformControlPanelDimension, controller, localizator);
        controlPanel.add(uniformControlPanel);

        exponentialControlPanel = new ExponentialControlPanel(exponentialControlPanelDimension, controller, localizator);
        controlPanel.add(exponentialControlPanel);

        normalControlPanel = new NormalControlPanel(normalControlPanelDimension, controller, localizator);
        controlPanel.add(normalControlPanel);

        determinedControlPanel = new DeterminedControlPanel(determinedControlPanelDimension, controller, localizator);
        controlPanel.add(determinedControlPanel);

        TunnelMenu menu = new TunnelMenu(this, controller, localizator);
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
        localizator.addLocalizable(this, Messages.MainTitle);

        localizator.setLanguage(Messages.Languages.English);
        updateSize();




        this.setVisible(true);
        controller.askForNotify();
        setParamRecursively(this);
        this.pack();
    }

    public static void setParamRecursively(Container container){
        container.setBackground(Color.BLACK);
        container.setForeground(new Color(236, 125, 0));
        if (container instanceof JButton){
            JButton jButton = (JButton) container;
            Dimension buttonDimension = new Dimension(140, 80);
            jButton.setMaximumSize(buttonDimension);
            jButton.setMinimumSize(buttonDimension);
            jButton.setSize(buttonDimension);
            jButton.setBackground(Color.WHITE);
            jButton.setForeground(Color.BLACK);
            return;
        }
        if (container instanceof JTextField){
            JTextField textField = (JTextField)container;
            Dimension textDimension = new Dimension(280, 80);
            textField.setMaximumSize(textDimension);
            textField.setSize(textDimension);
            textField.setBackground(Color.WHITE);
            textField.setForeground(Color.BLACK);
            return;
        }
        if (container instanceof JMenu){
            JMenu jMenu = (JMenu)container;
            MenuElement[] menus=jMenu.getSubElements();
            for(MenuElement menuElement:menus){
                Object o = menuElement.getComponent();
                if (o instanceof JPopupMenu){
                    JPopupMenu jPopupMenu = (JPopupMenu)menuElement.getComponent();
                    setParamRecursively(jPopupMenu);
                }
            }
        }
        if (container instanceof JMenuItem){
            JMenuItem jMenu = (JMenuItem)container;
            MenuElement[] menus=jMenu.getSubElements();
        }
        if (container instanceof JMenuBar){
            JMenuBar jMenu = (JMenuBar)container;
            MenuElement[] menus=jMenu.getSubElements();
        }
        if (container instanceof JScrollPane){
            return;
        }

        Component[] cs = container.getComponents();
        for(Component c:cs){
            if (Container.class.isAssignableFrom(c.getClass())){
                setParamRecursively((Container)c);
            }
        }
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
