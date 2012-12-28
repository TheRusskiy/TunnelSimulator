package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 28.12.12
 * Time: 8:04
 * To change this template use File | Settings | File Templates.
 */
public class TunnelMenu extends JMenuBar {

    public TunnelMenu(JFrame frame){
        //File  menu:
        JMenu fileMenu = new JMenu("File");
        this.add(fileMenu);
        //File menu items:
        JMenuItem exitFileItem = new JMenuItem("Exit");
        fileMenu.add(exitFileItem);

        //Car  menu:
        JMenu carMenu = new JMenu("Cars");
        this.add(carMenu);
        //Car menu items:
        JMenuItem showCarsItem = new JMenuItem("Show cars");
        carMenu.add(showCarsItem);

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
}
