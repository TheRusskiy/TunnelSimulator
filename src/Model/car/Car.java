package Model.car;

import Model.Coordinate;
import Model.Movable;

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
    public final static int MINIMUM_SPEED = 1;
    public static final int CAR_LENGTH = 5;

    public Car(CarModel model, int speed){
        this.model=model;
        this.speed=speed;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    @Override
    public void move(int time) {
        assert position!=null: "Car coordinate wasn't set!";
        //old coordinate occupier = null
        //new = this

        //todo implement
        assert false: "implement";
        //FIXME CHECK ROAD SPEED RESTRICTION!!!
    }






}
