package Model.car;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class CarModel implements Serializable {
    private int maxSpeed;
    private String name;
    private CarIcon icon;

    public CarModel(int maxSpeed, String name, CarIcon icon) {
        this.maxSpeed = maxSpeed;
        this.name = name;
        this.icon = icon;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getName() {
        return name;
    }

    public CarIcon getIcon() {
        return icon;
    }
}
