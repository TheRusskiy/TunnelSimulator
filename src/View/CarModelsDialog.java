package View;

import Controller.TunnelController;
import Model.car.CarModel;
import Model.car.CarModelsList;
import View.Utility.Localizator;
import View.Utility.NumberKeyFilter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 24.12.12
 * Time: 6:55
 * To change this template use File | Settings | File Templates.
 */
public class CarModelsDialog extends JFrame{

    private final String CAR_FILE_TYPE = "cars";
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

    private JButton addButton = new JButton("Add car");

    private JButton loadButton = new JButton("Load cars");

    private JButton saveButton = new JButton("Save cars");

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
//        localizator.addLocalizable(nameField, Messages.CarModelNameFiller);
        localizator.addLocalizable(speedLabel, Messages.CarModelSpeed);
        localizator.addLocalizable(addButton, Messages.CarModelAdd);
        localizator.addLocalizable(loadButton, Messages.CarModelLoad);
        localizator.addLocalizable(saveButton, Messages.CarModelSave);
        localizator.addLocalizable(deleteButton, Messages.CarModelDelete);


        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        this.setContentPane(parentPanel);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        captionsPanel = new JPanel();
        captionsPanel.setLayout(new GridLayout(1, 5));
        captionsPanel.add(nameLabel);
        captionsPanel.add(speedLabel);
        captionsPanel.add(rLabel);
        captionsPanel.add(gLabel);
        captionsPanel.add(bLabel);
        parentPanel.add(captionsPanel);


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
        controlsPanel.setLayout(new GridLayout(2,5));

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

        controlsPanel.add(deleteButton);
        controlsPanel.add(new JLabel());
        controlsPanel.add(loadButton);
        controlsPanel.add(saveButton);

        parentPanel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.AFTER_LAST_LINE);
        parentPanel.add(controlsPanel);


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2,5));
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(new JLabel(""));

        bottomPanel.add(new JLabel(""));
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(hideButton);
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(new JLabel(""));
        parentPanel.add(bottomPanel);



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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelected();
            }
        });

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
        tableModel.setColumnCount(5);
        tableModel.setColumnIdentifiers(new String[]{"Name", "Speed", "R", "G", "B"});
        for(CarModel mer: models){
            tableModel.addRow(new Object[]{
                    mer, //ROW CAR MODEL OBJECT
                    new Integer(mer.getMaxSpeed()).toString(),
                    new Integer(mer.getIcon().getR()).toString(),
                    new Integer(mer.getIcon().getG()).toString(),
                    new Integer(mer.getIcon().getB()).toString()
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

    private static <ObjectType> void saveToFile(File fileName, ObjectType objectToSave) throws IOException {
        File file=new File(fileName.getPath());
        file.getParentFile().mkdirs();
        file.createNewFile();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));){
            oos.writeObject(objectToSave);
        }
    }

    private static <ObjectType> ObjectType loadObjectFromFile(File file) throws IOException, ClassNotFoundException {
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
            FileNameExtensionFilter filter = new FileNameExtensionFilter( typeDescription, fileTypes);
            fc.setFileFilter(filter);
        }
        fc.setApproveButtonText(approveButton);
        fc.setDialogTitle(dialogTitle);
        int returnVal = fc.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            fileName = fc.getSelectedFile().getAbsolutePath();
        }
        if (fileName.equals("")) throw new DialogAbortedException("Open File Dialog exception, try again!");
        return fileName;
    }


    private void saveTo(){
        try {
            String file = getFileName(Messages.CarsFileDescription.getMessage(), new String[]{CAR_FILE_TYPE}, Messages.SaveCarsDialog.getMessage(), Messages.SaveCarsDialogAccept.getMessage(), this);
            file=file+"."+CAR_FILE_TYPE;
            saveToFile(new File(file), controller.getEngine().getCarGenerator().getModels());
            drawTable();
            this.pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFrom(){
        try {
            String file = getFileName(Messages.CarsFileDescription.getMessage(), new String[]{CAR_FILE_TYPE},  Messages.LoadCarsDialog.getMessage(),  Messages.LoadCarsDialogAccept.getMessage(), this);
            CarModelsList newModels = loadObjectFromFile(new File(file));
            controller.replaceCarModels(newModels);
            drawTable();
            this.pack();

        }catch (DialogAbortedException e){
            //NOTHING TO DO HERE :-)
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    Messages.CarsLoadExceptionMessage.getMessage(),
                    Messages.CarsLoadExceptionTitle.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
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

    private static class DialogAbortedException extends Exception{
        public DialogAbortedException() {
            super();
        }

        public DialogAbortedException(String message) {
            super(message);
        }
    }


}
