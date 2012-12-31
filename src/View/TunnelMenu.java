package View;

import Controller.TunnelController;
import Model.Engine;
import Model.car.CarModelsList;
import View.Utility.Localizator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 28.12.12
 * Time: 8:04
 * To change this template use File | Settings | File Templates.
 */
public class TunnelMenu extends JMenuBar {
    private TunnelController controller;
    private CarModelsDialog carModelsDialog;
    private final String CAR_FILE_TYPE = "cars";
    private TunnelView frame;

    public TunnelMenu(final TunnelView frame, final TunnelController controller, final Localizator localizator){
        this.controller=controller;
        this.frame=frame;
        carModelsDialog = new CarModelsDialog(frame, controller, localizator);
        frame.setParamRecursively(carModelsDialog, false);
        //File  menu:
        JMenu fileMenu = new JMenu("File");
        this.add(fileMenu);
        localizator.addLocalizable(fileMenu, Messages.File);

        //File menu items:
        JMenuItem exitFileItem = new JMenuItem("Exit");
        fileMenu.add(exitFileItem);
        localizator.addLocalizable(exitFileItem, Messages.Exit);

        //Car  menu:
        JMenu carMenu = new JMenu("Cars");
        this.add(carMenu);
        localizator.addLocalizable(carMenu, Messages.Cars);
        //Car menu items:
        JMenuItem showCarsItem = new JMenuItem("Show cars");
        carMenu.add(showCarsItem);
        localizator.addLocalizable(showCarsItem, Messages.ShowCars);

        JMenuItem loadCarsMenuItem = new JMenuItem("Show cars");
        carMenu.add(loadCarsMenuItem);
        localizator.addLocalizable(loadCarsMenuItem, Messages.CarModelLoad);
        JMenuItem saveCarsMenuItem = new JMenuItem("Show cars");
        carMenu.add(saveCarsMenuItem);
        localizator.addLocalizable(saveCarsMenuItem, Messages.CarModelSave);

        //Car  flow:
        JMenu flowMenu = new JMenu("Car flow");
        this.add(flowMenu);
        localizator.addLocalizable(flowMenu, Messages.CarFlow);
        //Flow menu items:
        JMenuItem normalFlowItem = new JMenuItem("Normal flow");
        flowMenu.add(normalFlowItem);
        localizator.addLocalizable(normalFlowItem, Messages.NormalFlow);

        JMenuItem exponentialFlowItem = new JMenuItem("Exponential flow");
        flowMenu.add(exponentialFlowItem);
        localizator.addLocalizable(exponentialFlowItem, Messages.ExponentialFlow);

        JMenuItem uniformFlowItem = new JMenuItem("Uniform flow");
        flowMenu.add(uniformFlowItem);
        localizator.addLocalizable(uniformFlowItem, Messages.UniformFlow);

        JMenuItem determinedFlowItem = new JMenuItem("Determined flow");
        flowMenu.add(determinedFlowItem);
        localizator.addLocalizable(determinedFlowItem, Messages.DeterminedFlow);

        //Language  menu:
        JMenu languageMenu = new JMenu("Language");
        this.add(languageMenu);
        localizator.addLocalizable(languageMenu, Messages.Language);

        //Language menu items:
        JMenuItem englishLanguageItem = new JMenuItem("English");
        languageMenu.add(englishLanguageItem);
        localizator.addLocalizable(englishLanguageItem, Messages.EnglishLanguage);

        JMenuItem russianLanguageItem = new JMenuItem("Russian");
        languageMenu.add(russianLanguageItem);
        localizator.addLocalizable(russianLanguageItem, Messages.RussianLanguage);

        exponentialFlowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeCarFlow(Engine.CarFlows.EXPONENTIAL);
            }
        });

        uniformFlowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeCarFlow(Engine.CarFlows.UNIFORM);
            }
        });
        determinedFlowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeCarFlow(Engine.CarFlows.DETERMINED);
            }
        });

        normalFlowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeCarFlow(Engine.CarFlows.NORMAL);
            }
        });

        showCarsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carModelsDialog.showDialog();
            }
        });
        englishLanguageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                localizator.setLanguage(Messages.Languages.English);
            }
        });
        russianLanguageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                localizator.setLanguage(Messages.Languages.Russian);
            }
        });

        saveCarsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTo();
            }
        });

        loadCarsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFrom();
            }
        });


        exitFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        this.add(getLookAndFeelMenu(frame));
    }

    private static JMenu getLookAndFeelMenu(final JFrame frame){
        JMenu lookAndFeelMenu = new JMenu("Look&Feel");
        //Get look&feel from system:
        final UIManager.LookAndFeelInfo[] looks=UIManager.getInstalledLookAndFeels();
        //Creating look and feel buttons
        final ButtonGroup lookGroup=new ButtonGroup();
        for(int i=0; i<looks.length; i++){
            final JRadioButtonMenuItem lookItem = new JRadioButtonMenuItem(looks[i].getName());
            //if this is current look and feel then select corresponding radio button:
            if(looks[i].getName().equals(UIManager.getLookAndFeel().getName())){
                lookItem.setSelected(true);
            }
            //add to menu and radio group:
            lookAndFeelMenu.add(lookItem);
            lookGroup.add(lookItem);
            //separator:
            lookAndFeelMenu.addSeparator();
            final int finalI = i;
            //Action listener
            lookItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        UIManager.setLookAndFeel(looks[finalI].getClassName());
                        SwingUtilities.updateComponentTreeUI(frame);
                        lookGroup.clearSelection();
                        lookGroup.setSelected(lookItem.getModel(), true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return lookAndFeelMenu;
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
            String file = getFileName(Messages.CarsFileDescription.getMessage(), new String[]{CAR_FILE_TYPE}, Messages.SaveCarsDialog.getMessage(), Messages.SaveCarsDialogAccept.getMessage(), frame);
            if (!file.contains("."+CAR_FILE_TYPE)){
                file=file+"."+CAR_FILE_TYPE;
            }

            saveToFile(new File(file), controller.getEngine().getCarGenerator().getModels());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFrom(){
        try {
            String file = getFileName(Messages.CarsFileDescription.getMessage(), new String[]{CAR_FILE_TYPE},  Messages.LoadCarsDialog.getMessage(),  Messages.LoadCarsDialogAccept.getMessage(), frame);
            CarModelsList newModels = loadObjectFromFile(new File(file));
            controller.replaceCarModels(newModels);

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

    private static class DialogAbortedException extends Exception{
        public DialogAbortedException() {
            super();
        }

        public DialogAbortedException(String message) {
            super(message);
        }
    }

}
