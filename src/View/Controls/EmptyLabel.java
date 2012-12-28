package View.Controls;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class EmptyLabel extends JLabel {
    enum Direction{
        X_AXIS, Y_AXIS;
    }
    public EmptyLabel(Container container, Direction direction){
        super();
        switch (direction){
            case Y_AXIS:
            {
                int preferredHeight = container.getPreferredSize().height;
                Component[] components = container.getComponents();
                for(Component component:components){
                    preferredHeight-=component.getPreferredSize().height;
                }
                this.setPreferredSize(new Dimension(1, preferredHeight));
                container.add(this);
                break;
            }
            case X_AXIS:
            {
                int preferredWidth = container.getPreferredSize().width;
                Component[] components = container.getComponents();
                for(Component component:components){
                    preferredWidth-=component.getPreferredSize().width;
                }
                this.setPreferredSize(new Dimension(preferredWidth, 1));
                container.add(this);
                break;
            }
            default:throw new NullPointerException();
        }

    }
}
