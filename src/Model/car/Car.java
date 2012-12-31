package Model.car;

import Model.Coordinate;
import Model.Movable;
import Model.Road;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class Car implements Movable {
    private boolean isSelected = false;
    private Coordinate position;
    private int speed;
    private CarModel model;
    private Road road;
    public final static int MINIMUM_SPEED = 0; //METERS!!
    public static final int CAR_LENGTH = 5;

    public Car(CarModel model, int speed){
        if (speed<MINIMUM_SPEED){
            speed=MINIMUM_SPEED;
        }
        this.model=model;
        this.speed=speed;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed<MINIMUM_SPEED){
            speed=MINIMUM_SPEED;
        }
        if (speed>model.getMaxSpeed()){
            speed=model.getMaxSpeed();
        }
        this.speed = speed;
    }

    public CarIcon getIcon(){
        return model.getIcon();
    }

    @Override
    public boolean move(int time) {
        assert position!=null: "Car coordinate wasn't set!";
        assert road!=null: "Car's road wasn't set!";
        if (speed>road.getSpeedLimitation()) speed=road.getSpeedLimitation();
        int wantsToDrive = speed*time;
        position=road.moveBy(position, wantsToDrive, CAR_LENGTH);
        if (position==null){
            return false;  //REACHED END OF TUNNEL
        }
        return true;
    }


    public void setSelected(boolean isSelected) {
        this.isSelected=isSelected;
    }

    public boolean isSelected(){
        return isSelected;
    }
}
