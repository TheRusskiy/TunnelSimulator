package Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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
        String currDirectory = currentDirectory();//System.getProperty("user.dir");
        if (isInsideJar()){
            createTempFileOutsideJar(fileName, RESOURCE_FOLDER);
            File insideJarFile = new File(currDirectory
                    +File.separator+fileName);
            open(insideJarFile);
        }else{
            File insideIdeFile = new File(currDirectory
                    //+File.separator+IDE_FOLDER
                    +File.separator+RESOURCE_FOLDER
                    +File.separator+fileName);
            open(insideIdeFile);
        }
    }

    private static void open(File document) throws IOException {
        //if(1==1) throw new IOException("inside open");
        Desktop dt = Desktop.getDesktop();
        dt.open(document);
    }
    private static void createTempFileOutsideJar(String name, String jarPath ) throws IOException{

        String currDirectory =  currentDirectory();
        //TempFile:
        File file = new File(currDirectory, name);
        file.createNewFile();
        file.deleteOnExit();

        JarEntry entry;
        FileInputStream fileInputStream = new FileInputStream(currDirectory+File.separator+JAR_NAME);
        JarInputStream jarInputStream = new JarInputStream((fileInputStream));

        entry=jarInputStream.getNextJarEntry();
        while(entry!=null){
            if (entry.getName().equals(jarPath+"/"+name)){
                OutputStream output = new FileOutputStream(file);
                try {
                    byte[] buffer = new byte[jarInputStream.available()];
                    for (int i = 0; i != -1; i = jarInputStream.read(buffer)) {
                        output.write(buffer, 0, i);
                    }
                }
                finally{
                    output.close();
                    jarInputStream.close();
                    }
                break;
            }
            entry=jarInputStream.getNextJarEntry();
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

    private static String currentDirectory()
    {
        Class cls = FileManager.class;
        ProtectionDomain pDomain = cls.getProtectionDomain();
        CodeSource cSource = pDomain.getCodeSource();
        URL loc = cSource.getLocation();
        String currClassDir = loc.getPath();
        if (isInsideJar()){
            currClassDir = currClassDir.substring(0, currClassDir.lastIndexOf(JAR_NAME));
        }
        return currClassDir;
    }

}
