package View.Utility;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 28.12.12
 * Time: 9:06
 * To change this template use File | Settings | File Templates.
 */
public class NumberKeyFilter {
    private static final int BACKSPACE_KEY_CODE =8;
    private static final int DELETE_KEY_CODE =8;
    public static void addFilterTo(JTextField textField, int min, int max){
        if (min<0) throw new RuntimeException("Min has to be positive!");
        if (max<=min) throw new RuntimeException("Max has to be bigger than min!");
        textField.addFocusListener(new KeyFocusListener(textField, min, max));
        textField.addKeyListener(new NumberKeyListener(textField, min, max));
        textField.setToolTipText(min+".."+max);
    }


    private static class NumberKeyListener implements KeyListener{
        private int min;
        private int max;
        private JTextField textField;

        public NumberKeyListener(JTextField textField, int min, int max){
            this.min=min;
            this.max=max;
            this.textField=textField;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (
                    (e.getKeyChar()>='0'&&e.getKeyChar()<='9')
                            ||e.getKeyCode()==BACKSPACE_KEY_CODE
                            ||e.getKeyCode()==DELETE_KEY_CODE
                    )
            {
            }
            else {
                e.consume();
            }
        }


        @Override
        public void keyPressed(KeyEvent e) {
            if (
                    (e.getKeyChar()>='0'&&e.getKeyChar()<='9')
                            ||e.getKeyCode()==BACKSPACE_KEY_CODE
                            ||e.getKeyCode()==DELETE_KEY_CODE
                    )
            {
            }
            else {
                e.consume();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyChar()<'0'||e.getKeyChar()>'9'){
                e.consume();
                return;
            }
            String text = textField.getText();
            try {
                int value = new Integer(text);
                if (value>max){
                    e.consume();
                    textField.setText(max + "");
                }
            } catch (NumberFormatException e1) {
                textField.setText(min+"");
            }
        }

    }


    private static class KeyFocusListener implements FocusListener{
        private int min;
        private int max;
        JTextField textField;
        public KeyFocusListener(JTextField textField, int min, int max){
            this.min=min;
            this.max=max;
            this.textField=textField;
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().equals("")){
                textField.setText(min+"");
            }
            String text = textField.getText();
            try {
                int value = new Integer(text);
                if (value<min){
                    textField.setText(min+"");
                }
                if (value>max){
                    textField.setText(max+"");
                }
            } catch (NumberFormatException e1) {
                textField.setText(min+"");
            }
        }

    }
}
