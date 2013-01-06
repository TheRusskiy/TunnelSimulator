package Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 06.01.13
 * Time: 7:59
 * To change this template use File | Settings | File Templates.
 */
public class FileManager {
    private static final String RESOURCE_FOLDER="resources";
    private static final String IDE_FOLDER="src";
    private static final String JAR_NAME="TunnelSimulator.jar";

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


    public static String getFileName(String typeDescription, String[] fileTypes,
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

    public static void openResource(String fileName) throws IOException{
        String currDirectory = System.getProperty("user.dir");
        if (isInsideJar()){
            //open(new File("F:\\cars.txt"));
            createTempFileOutsideJar(fileName, RESOURCE_FOLDER);
            File insideJarFile = new File(currDirectory
                    +File.separator+fileName);
            open(insideJarFile);
        }else{
            File insideIdeFile = new File(currDirectory
                    +File.separator+IDE_FOLDER
                    +File.separator+RESOURCE_FOLDER
                    +File.separator+fileName);
            open(insideIdeFile);
        }
    }

    private static void open(File document) throws IOException {
        Desktop dt = Desktop.getDesktop();
        dt.open(document);
    }
    private static void createTempFileOutsideJar(String name, String jarPath ) throws IOException{
        String currDirectory = System.getProperty("user.dir");
        //TempFile:
        File file = new File(currDirectory, name);
        file.createNewFile();
        file.deleteOnExit();

        JarFile jar = new JarFile(currDirectory+File.separator+JAR_NAME);
        JarEntry entry=jar.getJarEntry(jarPath + "/" + name);
        InputStream input = jar.getInputStream(entry);
        OutputStream output = new FileOutputStream(file);
        try {
            byte[] buffer = new byte[input.available()];
            for (int i = 0; i != -1; i = input.read(buffer)) {
                output.write(buffer, 0, i);
            }
        } finally {
            jar.close();
            input.close();
            output.close();
        }
    }

    private static boolean isInsideJar(){
        String className = FileManager.class.getName().replace('.', '/');
        String classJar =  FileManager.class.getResource("/" + className + ".class").toString();
        if (classJar.startsWith("jar:")) {
            return true;
        }else{
            return false;
        }
    }

}
