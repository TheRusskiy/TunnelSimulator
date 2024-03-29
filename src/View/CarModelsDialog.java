package View;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.car.CarModel;
import Model.car.CarModelsList;
import View.Utility.Localizator;
import View.Utility.NumberKeyFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 24.12.12
 * Time: 6:55
 * To change this template use File | Settings | File Templates.
 */
public class CarModelsDialog extends JFrame implements ModelListener{


    private JButton hideButton = new JButton("OK");
    private JFrame parentFrame;
    private JPanel parentPanel;

    private JPanel captionsPanel;
    private JPanel tablePanel;
    private JTable jTable;
    private JPanel controlsPanel;
    private JPanel bottomPanel;
    private DefaultTableModel tableModel;

    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameField = new JTextField("New Name");

    private JLabel speedLabel = new JLabel("Max Speed(m/s):");
    private JTextField speedField = new JTextField("10");

    private JLabel rLabel = new JLabel(" R:");
    private JTextField rField = new JTextField("200");

    private JLabel gLabel = new JLabel(" G:");
    private JTextField gField = new JTextField("50");

    private JLabel bLabel = new JLabel(" B:");
    private JTextField bField = new JTextField("50");

    private JLabel pictureLabel = new JLabel("Picture:");

    private JButton addButton = new JButton("Add car");

//    private JButton loadButton = new JButton("Load cars");
//
//    private JButton saveButton = new JButton("Save cars");

    private JButton deleteButton = new JButton("Delete cars");

    private Localizator localizator;


    private TunnelController controller;

    public CarModelsDialog(JFrame parentFrame, final TunnelController controller, final Localizator localizator) {
       // super("Car Models");
        this.setTitle("Car Models");
        this.parentFrame=parentFrame;
        this.controller=controller;
        this.localizator=localizator;
        localizator.addLocalizable(nameLabel, Messages.CarModelName);
        localizator.addLocalizable(speedLabel, Messages.CarModelSpeed);
        localizator.addLocalizable(addButton, Messages.CarModelAdd);
        localizator.addLocalizable(deleteButton, Messages.CarModelDelete);
        localizator.addLocalizable(pictureLabel, Messages.CarPictureLabel);


        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        this.setContentPane(parentPanel);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        captionsPanel = new JPanel();
        captionsPanel.setLayout(new GridLayout(1, 6));
        captionsPanel.add(nameLabel);
        captionsPanel.add(speedLabel);
        captionsPanel.add(rLabel);
        captionsPanel.add(gLabel);
        captionsPanel.add(bLabel);
        captionsPanel.add(pictureLabel);
        parentPanel.add(captionsPanel);


        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 1));

        tableModel= new DefaultTableModel(){//Make non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        jTable = new JTable(tableModel){
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        jTable.getTableHeader().setReorderingAllowed(false);
        tablePanel.add(jTable);
        JScrollPane tableScrollPanel=new JScrollPane(tablePanel);
        parentPanel.add(tableScrollPanel);

        controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(2,6));

        controlsPanel.add(nameField);
        controlsPanel.add(speedField);
        controlsPanel.add(rField);
        controlsPanel.add(gField);
        controlsPanel.add(bField);
        controlsPanel.add(addButton);

        NumberKeyFilter.addFilterTo(speedField, CarModel.MINIMUM_MODEL_SPEED, CarModel.MAXIMUM_MODEL_SPEED);
        NumberKeyFilter.addFilterTo(rField, 0, 255);
        NumberKeyFilter.addFilterTo(gField, 0, 255);
        NumberKeyFilter.addFilterTo(bField, 0, 255);

        controlsPanel.add(new JLabel());
        controlsPanel.add(new JLabel());
        controlsPanel.add(new JLabel());
        controlsPanel.add(new JLabel());
        controlsPanel.add(new JLabel());
        controlsPanel.add(deleteButton);

        parentPanel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.AFTER_LAST_LINE);
        parentPanel.add(controlsPanel);


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,3));

        bottomPanel.add(new JLabel(""));
        bottomPanel.add(hideButton);
        bottomPanel.add(new JLabel(""));
        Dimension bottomPanelDimension = new Dimension(
                bottomPanel.getPreferredSize().width*2,
                bottomPanel.getPreferredSize().height*2
        );
        bottomPanel.setPreferredSize(bottomPanelDimension);
        parentPanel.add(bottomPanel);



        localizator.addLocalizable(this, Messages.CarModelsTitle);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocation(parentFrame.getBounds().getLocation());
        

        hideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCar();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelected();
            }
        });
        controller.registerListener(this);
        this.pack();
    }

    public void showDialog(){
        //parentFrame.disable();
        drawTable();
        this.pack();
        this.setVisible(true);
    }

    private void cancel() {
        //parentFrame.enable();
        this.setVisible(false);
    }

    private void drawTable(){
        CarModelsList models = controller.getEngine().getCarGenerator().getModels();
        tableModel.setNumRows(0);
        tableModel.setColumnCount(6);
        tableModel.setColumnIdentifiers(new String[]{"Name", "Speed", "R", "G", "B", "Picture"});
        for(CarModel mer: models){
            tableModel.addRow(new Object[]{
                    mer, //ROW CAR MODEL OBJECT
                    new Integer(mer.getMaxSpeed()).toString(),
                    new Integer(mer.getIcon().getR()).toString(),
                    new Integer(mer.getIcon().getG()).toString(),
                    new Integer(mer.getIcon().getB()).toString(),
                    new ImageIcon(mer.getIcon().getImage())
            });
        }
    }
    private void addCar(){
        controller.addCarModel(speedField.getText(),
                nameField.getText(),
                rField.getText(),
                gField.getText(),
                bField.getText()
                );
        drawTable();
        this.pack();
    }



    private void deleteSelected(){
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow==-1){
            JOptionPane.showMessageDialog(this,
                    Messages.CarsDeleteNoneSelectedDialogMessage.getMessage(),
                    Messages.CarsDeleteNoneSelectedDialogTitle.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }else{
            CarModel selectedModel = (CarModel)tableModel.getValueAt(selectedRow, 0);
            controller.deleteModel(selectedModel);
            drawTable();
            this.pack();
        }
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

    }

    @Override
    public void notifyOfCarModelsChange() {
        drawTable();
        this.pack();
    }
}
