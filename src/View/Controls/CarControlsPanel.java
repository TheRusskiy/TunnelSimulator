package View.Controls;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Engine;
import Model.car.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private JTextField speedTextField = new JTextField();
    private JButton speedApplyButton = new JButton("Apply speed");

    private TunnelController controller;
    private Engine engine;
    private Car previouslySelectedCar;

    public CarControlsPanel(Dimension preferredSize, final TunnelController controller){
        this.controller=controller;
        this.engine=controller.getEngine();
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

        EmptyLabel emptyLabel = new EmptyLabel(this, EmptyLabel.Direction.Y_AXIS);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nextCar();
            }
        });
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.previousCar();
            }
        });
        speedApplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String sad = ((JTextField).getFocusOwner()).getText();
                controller.changeSelectedCarSpeed(speedTextField.getText());
            }
        });

//        speedTextField.addFocusListener(new FocusListener() {
//
//            @Override
//            public void focusGained(FocusEvent e) {
//                speedIsFocused=true;
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                speedIsFocused=false;
//                Object s = e.getComponent();
//                int i=0;
//            }
//        });

//        speedTextField.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                int i=0;
//                //if speedI
//            }
//        });
    }

    private void showSelectedCarSpeed(){

        Car selectedCar=engine.getSelectedCar();
        if (selectedCar!=previouslySelectedCar){
            int speed;
            if (selectedCar!=null){
                speed = selectedCar.getSpeed();
            }
            else{
                speed=0;
            }
            speedTextField.setText(speed+"");
            previouslySelectedCar=selectedCar;
        }

//        if (!speedTextField.isFocusOwner()){
//            speedTextField.setText(speed+"");
//        }
//        if (!speedIsFocused) speedTextField.setText(speed+"");

    }

    @Override
    public void notifyOfDataChange() {
        showSelectedCarSpeed();
    }

    @Override
    public void notifyOfPropertiesChange() {
        showSelectedCarSpeed();
    }

    @Override
    public void notifyOfStructureChange() {

    }
}
