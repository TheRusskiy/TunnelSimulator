package Model.car;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class CarIcon implements Serializable {
    transient private BufferedImage icon;
    private int r;
    private int g;
    private int b;

    public BufferedImage getImage() {
        if (icon==null){
            icon=new BufferedImage(20, 12, BufferedImage.TYPE_INT_ARGB);
            Graphics2D iconGraphics = icon.createGraphics();
            iconGraphics.setColor(new Color(r, g, b));
            iconGraphics.fillRect(0, 0, 20, 12);
            iconGraphics.setColor(Color.BLACK);
            iconGraphics.drawRect(0, 0, 20, 12);
            icon.setAccelerationPriority(1);
        }
        return icon;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public CarIcon(){
        icon = new BufferedImage(20, 12, BufferedImage.TYPE_INT_ARGB);
        Graphics2D iconGraphics = icon.createGraphics();
        iconGraphics.setColor(new Color(r, g, b));
        iconGraphics.fillRect(0, 0, 20, 12);
        iconGraphics.setColor(Color.BLACK);
        iconGraphics.drawRect(0, 0, 20, 12);
        icon.setAccelerationPriority(1);
    }

    public CarIcon(int r, int g, int b) {
        if (r>255){
            r=255;
        }
        if (r<0){
            r=0;
        }
        if (g>255){
            g=255;
        }
        if (g<0){
            g=0;
        }
        if (b>255){
            b=255;
        }
        if (b<0){
            b=0;
        }
        this.r=r;
        this.g=g;
        this.b=b;
        icon = new BufferedImage(20, 12, BufferedImage.TYPE_INT_ARGB);
        Graphics2D iconGraphics = icon.createGraphics();
        iconGraphics.setColor(new Color(r, g, b));
        iconGraphics.fillRect(0, 0, 20, 12);
        iconGraphics.setColor(Color.BLACK);
        iconGraphics.drawRect(0, 0, 20, 12);
        icon.setAccelerationPriority(1);
    }
}
