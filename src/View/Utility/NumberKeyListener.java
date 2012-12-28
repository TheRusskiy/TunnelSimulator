package View.Utility;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 28.12.12
 * Time: 9:06
 * To change this template use File | Settings | File Templates.
 */
public class NumberKeyListener implements KeyListener{
    private int min;
    private int max;
    private static final int BACKSPACE_KEY_CODE =8;
    private static final int DELETE_KEY_CODE =8;
    public NumberKeyListener(int min, int max){
        this.min=min;
        this.max=max;
    }
    FocusListener focusListener = null;
    @Override
    public void keyTyped(KeyEvent e) {
        final JTextField textField = (JTextField)e.getComponent();
        if (focusListener==null){
            focusListener = new KeyFocusListener(textField);
            textField.addFocusListener(focusListener);
        }
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
        final JTextField textField = (JTextField)e.getComponent();
        if (focusListener==null){
            focusListener = new KeyFocusListener(textField);
            textField.addFocusListener(focusListener);
        }
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
        final JTextField textField = (JTextField)e.getComponent();
        if (focusListener==null){
            focusListener = new KeyFocusListener(textField);
            textField.addFocusListener(focusListener);
        }
        String text = textField.getText();
        //text=text+e.getKeyChar();
        try {
            int value = new Integer(text);
//            if (value<min){
//                e.consume();
//                textField.setText(min + "");
//            }
            if (value>max){
                e.consume();
                textField.setText(max + "");
            }
        } catch (NumberFormatException e1) {
            textField.setText(min+"");
        }
    }

    class KeyFocusListener implements FocusListener{
        JTextField textField;
        public KeyFocusListener(JTextField textField){
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
