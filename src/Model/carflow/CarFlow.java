package Model.carflow;

import Model.Engine;
import Model.car.Car;
import Model.car.CarGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public abstract class CarFlow {
    private CarGenerator carGenerator;
    protected Engine engine;
    public CarFlow(Engine engine){
        this.engine=engine;
        carGenerator=engine.getCarGenerator();
    }

//    public void setCarGenerator(CarGenerator carGenerator) {
//        assert carGenerator!=null: "Car flow received null car Generator";
//        this.carGenerator = carGenerator;
//    }

    public Car getCar(int  time){
        if (hasNextCar(time)){
            return carGenerator.createCar();
        }
        else return null;
    }

    abstract boolean hasNextCar(int time);

}
