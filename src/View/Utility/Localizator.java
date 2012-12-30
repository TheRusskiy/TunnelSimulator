package View.Utility;

import View.Messages;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 30.12.12
 * Time: 2:09
 * To change this template use File | Settings | File Templates.
 */
public class Localizator {
    private List<Localizable> localizables = new LinkedList<>();
    public Localizator(){

    }
    public Localizator(Messages.Languages language){
        Messages.setLanguage(language);
    }

    public void setLanguage(Messages.Languages language){
        Messages.setLanguage(language);
        for(Localizable localizable: localizables){
            localizable.refreshText();
        }
    }
    public void addLocalizable(JLabel field, Messages message){
        localizables.add(new Localizable(field, message));
    }
    public void addLocalizable(JTextField field, Messages message){
        localizables.add(new Localizable(field, message));
    }
    public void addLocalizable(JMenu field, Messages message){
        localizables.add(new Localizable(field, message));
    }
    public void addLocalizable(JMenuItem field, Messages message){
        localizables.add(new Localizable(field, message));
    }
    public void addLocalizable(JButton field, Messages message){
        localizables.add(new Localizable(field, message));
    }
    public void addLocalizable(JFrame field, Messages message){
        localizables.add(new Localizable(field, message));
    }


    private class Localizable{
        JLabel jLabel;
        JTextField jTextField;
        JMenu jMenu;
        JMenuItem jMenuItem;
        JButton jButton;
        JFrame jFrame;

        Messages message;

        public Localizable(JLabel field, Messages message){
            this.jLabel =field;
            this.message=message;
        }
        public Localizable(JTextField field, Messages message){
            this.jTextField=field;
            this.message=message;
        }
        public Localizable(JMenu field, Messages message){
            this.jMenu=field;
            this.message=message;
        }
        public Localizable(JMenuItem field, Messages message){
            this.jMenuItem=field;
            this.message=message;
        }
        public Localizable(JButton field, Messages message){
            this.jButton=field;
            this.message=message;
        }
        public Localizable(JFrame field, Messages message){
            this.jFrame=field;
            this.message=message;
        }

        public void refreshText(){
            if (jLabel !=null){
                jLabel.setText(message.getMessage());
                return;
            }
            if (jTextField!=null){
                jTextField.setText(message.getMessage());
                return;
            }
            if (jMenu!=null){
                jMenu.setText(message.getMessage());
                return;
            }
            if (jMenuItem!=null){
                jMenuItem.setText(message.getMessage());
                return;
            }
            if (jButton!=null){
                jButton.setText(message.getMessage());
                return;
            }
            if (jFrame!=null){
                jFrame.setTitle(message.getMessage());
                return;
            }
        }

    }

}
