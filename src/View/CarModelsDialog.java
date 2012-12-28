package View;

import Controller.TunnelController;
import Model.car.Car;
import Model.car.CarIcon;
import Model.car.CarModel;
import View.Utility.NumberKeyListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 24.12.12
 * Time: 6:55
 * To change this template use File | Settings | File Templates.
 */
public class CarModelsDialog extends JFrame{

    private JButton hideButton = new JButton("Hide");
    private JFrame parentFrame;
    private JPanel parentPanel;

    private JPanel tablePanel;
    private JTable jTable;
    private JPanel controlsPanel;
    private JPanel bottomPanel;
    private DefaultTableModel tableModel;

    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameField = new JTextField("New Name");

    private JLabel speedLabel = new JLabel("Max Speed(m/s):");
    private JTextField speedField = new JTextField("10");

    private JLabel rLabel = new JLabel("R:");
    private JTextField rField = new JTextField("200");

    private JLabel gLabel = new JLabel("G:");
    private JTextField gField = new JTextField("50");

    private JLabel bLabel = new JLabel("B:");
    private JTextField bField = new JTextField("50");

    private JButton addButton = new JButton("Add car");

    private JButton loadButton = new JButton("Load cars");

    private JButton saveButton = new JButton("Save cars");


    private TunnelController controller;

    public CarModelsDialog(JFrame parentFrame, final TunnelController controller) {
       // super("Car Models");
        this.setTitle("Car Models");
        this.parentFrame=parentFrame;
        this.controller=controller;


        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        this.setContentPane(parentPanel);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 1));

        tableModel= new DefaultTableModel(){//Make non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        jTable = new JTable(tableModel);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        //dataTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox(new String[]{"12","23"})));
        jTable.getTableHeader().setReorderingAllowed(false);
        tablePanel.add(jTable);
        JScrollPane tableScrollPanel=new JScrollPane(tablePanel);
        parentPanel.add(tableScrollPanel);

        controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(3,5));

        controlsPanel.add(nameLabel);
        controlsPanel.add(speedLabel);
        controlsPanel.add(rLabel);
        controlsPanel.add(gLabel);
        controlsPanel.add(bLabel);
        controlsPanel.add(nameField);
        controlsPanel.add(speedField);
        controlsPanel.add(rField);
        controlsPanel.add(gField);
        controlsPanel.add(bField);
        controlsPanel.add(addButton);

        speedField.addKeyListener(
        new NumberKeyListener(Car.MINIMUM_SPEED, 200));
        rField.addKeyListener(
                new NumberKeyListener(0, 255));
        gField.addKeyListener(
                new NumberKeyListener(0, 255));
        bField.addKeyListener(
                new NumberKeyListener(0, 255));

        controlsPanel.add(new JLabel());
        controlsPanel.add(new JLabel());
        controlsPanel.add(loadButton);
        controlsPanel.add(saveButton);

        parentPanel.add(controlsPanel);



        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,1));
        bottomPanel.add(hideButton);
        parentPanel.add(bottomPanel);


//        this.setPreferredSize(new Dimension(300, 150));

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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTo();
            }
        });
       loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFrom();
            }
        });

        this.pack();
    }

    public void showDialog(){
        parentFrame.disable();
        drawTable(controller.getEngine().getCarGenerator().getModels());
        this.pack();
        this.setVisible(true);
    }

    private void cancel() {
        parentFrame.enable();
        this.setVisible(false);
    }

    public void drawTable(java.util.List<CarModel> models){
        tableModel.setNumRows(0);
        tableModel.setColumnIdentifiers(new String[]{"Name", "Speed", "R", "G", "B"});
        for(CarModel mer: models){
            tableModel.addRow(new String[]{
                    mer.getName().toString(),
                    new Integer(mer.getMaxSpeed()).toString(),
                    new Integer(mer.getIcon().getR()).toString(),
                    new Integer(mer.getIcon().getG()).toString(),
                    new Integer(mer.getIcon().getB()).toString()
            });
        }
    }
    public void addCar(){
        int speed = new Integer(speedField.getText());
        int r = new Integer(rField.getText());
        int g = new Integer(gField.getText());
        int b = new Integer(bField.getText());
        CarIcon icon = new CarIcon(r, g, b);
        CarModel model = new CarModel(speed, nameField.getText(), icon);

        controller.getEngine().getCarGenerator().getModels().add(model);
        drawTable(controller.getEngine().getCarGenerator().getModels());
        this.pack();
    }

    public static <ObjectType> void saveToFile(File fileName, ObjectType objectToSave) throws IOException {
        File file=new File(fileName.getPath());
        file.getParentFile().mkdirs();
        file.createNewFile();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));){

            oos.writeObject(objectToSave);
        }
    }

    public static <ObjectType> ObjectType loadObjectFromFile(File file) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            return (ObjectType)ois.readObject();
        }
    }


    private static String getFileName(String typeDescription, String[] fileTypes,
                                      String dialogTitle, String approveButton, JFrame frame)
            throws Exception  {
        String fileName ="";
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (typeDescription!=null)
        {
            //FileNameExtensionFilter filter = new FileNameExtensionFilter( typeDescription, fileTypes);
            //fc.setFileFilter(filter);
        }
        fc.setApproveButtonText(approveButton);
        fc.setDialogTitle(dialogTitle);
        int returnVal = fc.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            fileName = fc.getSelectedFile().getAbsolutePath();
        }
        if (fileName.equals("")) throw new Exception("Open File Dialog exception, try again!");
        return fileName;
    }


    public void saveTo(){
        try {
            String file = getFileName("", new String[]{}, "Save car models", "Save", this);
            saveToFile(new File(file), controller.getEngine().getCarGenerator().getModels());
            drawTable(controller.getEngine().getCarGenerator().getModels());
            this.pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFrom(){
        try {
            String file = getFileName("", new String[]{}, "Load car models", "Load", this);
            LinkedList<CarModel> newModels = loadObjectFromFile(new File(file));
            LinkedList<CarModel> oldModels= controller.getEngine().getCarGenerator().getModels();
            oldModels.clear();
            oldModels.addAll(newModels);
            drawTable(controller.getEngine().getCarGenerator().getModels());
            this.pack();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
