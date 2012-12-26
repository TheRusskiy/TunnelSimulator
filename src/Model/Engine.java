package Model;

import Model.car.Car;
import Model.carflow.CarFlow;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class Engine {
    private LinkedList<Movable> cars = new LinkedList<>();
    private int timePast;
    private int stepTime;
    private static final int DEFAULT_SPEED_LIMIT=60;
    private Road road = new Road(DEFAULT_SPEED_LIMIT);
    private CarFlow carFlow;
    private TimeThread timeThread;

    public void setStepTime(int stepTime) {
        this.stepTime = stepTime;
    }

    public Engine(CarFlow carFlow){
        assert carFlow!=null:"Engine received null carFlow";
        this.carFlow=carFlow;

        timeThread = new TimeThread(this);
        timeThread.setDaemon(true);
        timeThread.start();
    }

    public void enableAuto(){
        timeThread.setEnabled(true);
    }
    public void disableAuto(){
        timeThread.setEnabled(false);
    }

    public void step(){
        assert stepTime!=0:"SET STEP TIME!";
        step(stepTime);
    }

    private void step(int time){
        moveAllFromLastToFirst(cars, time);
        Car possibleCar = createCarAtTheBeginningIfPossible(time);
        if (possibleCar!=null) cars.push(possibleCar);
    }

    private void moveAllFromLastToFirst(LinkedList<Movable> movables, int time) {
        for(int i=movables.size()-1; i>=0; i--){
            movables.get(i).move(time);
        }
    }

    private Car createCarAtTheBeginningIfPossible(int time){
        Coordinate roadStart=road.getStartingCoordinate();
        if (road.isNotOccupied(roadStart, Car.CAR_LENGTH)){
            Car possibleCar = carFlow.getCar(time);
            if (possibleCar!=null)
            {
                possibleCar.setPosition(road.nextCoordinate(roadStart, Car.CAR_LENGTH));
                road.moveBy(roadStart, Car.CAR_LENGTH-1, Car.CAR_LENGTH); // Set starting coordinates to be occupied
                return possibleCar;
            }
        }
        return null;
    }

}
