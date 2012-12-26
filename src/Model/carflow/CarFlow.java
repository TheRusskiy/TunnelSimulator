package Model.carflow;

import Model.car.CarGenerator;
import Model.car.Car;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public abstract class CarFlow {
    private CarGenerator carGenerator;
    public CarFlow(CarGenerator carGenerator){
        assert carGenerator!=null: "Car flow received null car Generator";
        this.carGenerator=carGenerator;
    }

    public Car getCar(int  time){
        if (hasNextCar(time)){
            return carGenerator.createCar();
        }
        else return null;
    }

    abstract boolean hasNextCar(int time);

}
