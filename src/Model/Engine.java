package Model;

import Controller.ModelListener;
import Model.car.Car;
import Model.carflow.CarFlow;
import View.VisualPanel;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

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
    private static final int DEFAULT_ROAD_LENGTH=200;
    private Road road = new Road(DEFAULT_SPEED_LIMIT, DEFAULT_ROAD_LENGTH);
    private CarFlow carFlow;
    private TimeThread timeThread;
    private VisualPanel visualPanel;
    private int carsOutOfRange=0;
    private int carsOutOfRangeToGC=100;
    private Set<ModelListener> listeners= new LinkedHashSet<>();

    public static final boolean DEBUG_MODE = false;

    public Road getRoad() {
        return road;
    }

    public int getTimePast() {
        return timePast;
    }

    public void setStepTime(int stepTime) {
        this.stepTime = stepTime;
    }

    public Engine(CarFlow carFlow){
        assert carFlow!=null:"Engine received null carFlow";
        this.carFlow=carFlow;
        timePast=0;

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
    public void setAutoTickTime(double tickTimeInSeconds){
        timeThread.setTickTimeInSeconds(tickTimeInSeconds);
    }

    public void setVisualPanel(VisualPanel visualPanel) {
        this.visualPanel = visualPanel;
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
        notifyListenersOfDataChange();
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
        carsOutOfRange+=toExcludeFromRoad.size();
        if (carsOutOfRange>=carsOutOfRangeToGC){
            System.gc();
            carsOutOfRange=0;
        }
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

    public void registerListener(ModelListener listener){
        listeners.add(listener);
    }

    private void notifyListenersOfDataChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfDataChange();
        }
    }

    private void notifyListenersOfStructureChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfStructureChange();
        }
    }

    private void notifyListenersOfPropertiesChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfPropertiesChange();
        }
    }

    public void notifyAllListeners(){
        notifyListenersOfDataChange();
        notifyListenersOfPropertiesChange();
        notifyListenersOfStructureChange();
    }


}
