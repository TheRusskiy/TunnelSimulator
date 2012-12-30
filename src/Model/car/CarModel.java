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
    public final static int MINIMUM_MODEL_SPEED = 1;
    public final static int MAXIMUM_MODEL_SPEED = 100;

    public CarModel(int maxSpeed, String name, CarIcon icon) {
        //if (maxSpeed<=0) throw new RuntimeException("Car model's maxSpeed has to be positive");
        if (maxSpeed<MINIMUM_MODEL_SPEED){
            maxSpeed=MINIMUM_MODEL_SPEED;
        }
        if (maxSpeed>MAXIMUM_MODEL_SPEED){
            maxSpeed=MAXIMUM_MODEL_SPEED;
        }
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
