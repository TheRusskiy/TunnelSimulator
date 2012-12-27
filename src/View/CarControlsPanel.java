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
public class CarControlsPanel extends JPanel implements ModelListener{
    private JLabel title = new JLabel("Car controls");

    private JButton nextButton = new JButton("Next Car");
    private JButton previousButton = new JButton("Previous Car");

    private JLabel speedLabel = new JLabel("Speed:");
    //TODO get load default values from somewhere to both model and view
    private JTextField speedTextField = new JTextField();
    private JButton speedApplyButton = new JButton("Apply speed");

    private TunnelController controller;

    public CarControlsPanel(Dimension preferredSize, TunnelController controller){
        this.controller=controller;
        controller.registerListener(this);

        this.add(title);
        this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.LINE_START);
        this.add(nextButton);
        this.add(previousButton);
        this.add(speedLabel);
        this.add(speedTextField);
        this.add(speedApplyButton);

        BoxLayout layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutManager);

        this.setPreferredSize(preferredSize);
        this.setSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

//        Component[] components = this.getComponents();
//        for(Component component:components){
//            component.setSize(
//                    new Dimension(
//                            preferredSize.width,component.getPreferredSize().height
//                    )
//            );
//        }

        EmptyLabel emptyLabel = new EmptyLabel(this, EmptyLabel.Direction.Y_AXIS);
    }

    @Override
    public void notifyOfChange() {

    }
}
