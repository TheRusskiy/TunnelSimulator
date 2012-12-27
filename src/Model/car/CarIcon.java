package Model.car;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class CarIcon {
    private BufferedImage icon;

    public BufferedImage getImage() {
        return icon;
    }

    public CarIcon() {
        icon = new BufferedImage(20, 12, BufferedImage.TYPE_INT_ARGB);
        Graphics2D iconGraphics = icon.createGraphics();
        iconGraphics.setColor(Color.RED);
        iconGraphics.fillRect(0, 0, 20, 12);
        iconGraphics.setColor(Color.BLACK);
        iconGraphics.drawRect(0, 0, 20, 12);
        icon.setAccelerationPriority(1);
    }
}
