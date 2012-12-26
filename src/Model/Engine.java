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
    private static final int DEFAULT_SPEED_LIMIT=16; //16~60Km/h
    private Road road = new Road(DEFAULT_SPEED_LIMIT);
    private CarFlow carFlow;
    private TimeThread timeThread;
    public static final boolean DEBUG_MODE = true;

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
    public void setAutoTickTime(int tickTime){
        timeThread.setTickTimeInSeconds(tickTime);
    }

    public void step(){
        assert stepTime!=0:"SET STEP TIME!";
        step(stepTime);
    }

    private void step(int time){
        timePast+=time;
        moveAllFromLastToFirst(cars, time);
        Car possibleCar = createCarAtTheBeginningIfPossible(time);
        if (possibleCar!=null){
            cars.push(possibleCar);
            possibleCar.setRoad(road);
        }
        road.textVisualize(timePast);
    }

    private void moveAllFromLastToFirst(LinkedList<Movable> movables, int time) {
        LinkedList<Movable> toExcludeFromRoad = new LinkedList<>();
        Movable current;
        for(int i=movables.size()-1; i>=0; i--){
            road.textVisualize(timePast);
            current=movables.get(i);
            if (current.move(time)==false){
                toExcludeFromRoad.add(current);
            }
        }
        road.textVisualize(timePast);
        movables.removeAll(toExcludeFromRoad);
    }

    private Car createCarAtTheBeginningIfPossible(int time){
        Coordinate roadStart=road.getStartingCoordinate();
        if (road.isNotOccupied(roadStart, Car.CAR_LENGTH)){
            Car possibleCar = carFlow.getCar(time);
            if (possibleCar!=null)
            {
                possibleCar.setPosition(road.nextCoordinate(roadStart, Car.CAR_LENGTH-1));
                road.nextCoordinate(roadStart, Car.CAR_LENGTH-1).setOccupier(possibleCar);
                road.setOccupation(roadStart.getXAxis(), Car.CAR_LENGTH - 1, true); // Set starting coordinates to be occupied
                return possibleCar;
            }
        }
        return null;
    }

}
