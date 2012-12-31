package Model;

import Controller.ModelListener;
import Model.car.Car;
import Model.car.CarGenerator;
import Model.car.CarModelsList;
import Model.carflow.*;

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
    public static final int MINIMUM_STEP_TIME=1;
    public static final int MAXIMUM_STEP_TIME=10;
    private static final int DEFAULT_SPEED_LIMIT=16; //16~60Km/h
    private static final int DEFAULT_ROAD_LENGTH=200;
    public static final boolean DEBUG_MODE = false;

    private int timePast;
    private boolean no_selected_car =true;
    private volatile int stepTime=MINIMUM_STEP_TIME;
    private Road road = new Road(DEFAULT_SPEED_LIMIT, DEFAULT_ROAD_LENGTH);
    private TimeThread timeThread;
    private int carsOutOfRange=0;
    private int carsOutOfRangeToGC=100;
    private Car selectedCar = null;
    private Set<ModelListener> listeners= new LinkedHashSet<>();
    private CarGenerator carGenerator;
    private CarFlow carFlow;
    private UniformCarFlow uniformCarFlow;
    private ExponentialCarFlow exponentialCarFlow;
    private DeterminedCarFlow determinedCarFlow;
    private NormalCarFlow normalCarFlow;
    public enum CarFlows{
        UNIFORM,
        EXPONENTIAL,
        DETERMINED,
        NORMAL
    }

    public Road getRoad() {
        return road;
    }

    public CarGenerator getCarGenerator(){
        return carGenerator;
    }

    public int getTimePast() {
        return timePast;
    }

    public void setStepTime(int stepTime) {
        if (stepTime<MINIMUM_STEP_TIME){
            stepTime=MINIMUM_STEP_TIME;
        }
        if (stepTime>MAXIMUM_STEP_TIME){
            stepTime=MAXIMUM_STEP_TIME;
        }
        this.stepTime = stepTime;
        notifyListenersOfPropertiesChange();
    }

    public void replaceCarModels(CarModelsList newModels){
        CarModelsList oldModels = getCarGenerator().getModels();
        oldModels.replaceModels(newModels);
        notifyListenersOfCarModelsChange();
    }

    public void setSpeedLimit(int speedLimit) {
        road.setSpeedLimitation(speedLimit);
        notifyListenersOfPropertiesChange();
    }

    public DeterminedCarFlow getDeterminedCarFlow() {
        return determinedCarFlow;
    }

    public UniformCarFlow getUniformCarFlow() {
        return uniformCarFlow;
    }

    public NormalCarFlow getNormalCarFlow() {
        return normalCarFlow;
    }

    public ExponentialCarFlow getExponentialCarFlow() {
        return exponentialCarFlow;
    }

    public CarFlow getCarFlow() {
        return carFlow;
    }

    public void setCarFlow(CarFlows flow_enum) {
        switch (flow_enum){
            case DETERMINED:{
                setCarFlow(determinedCarFlow);
                break;
            }
            case UNIFORM:{
                setCarFlow(uniformCarFlow);
                break;
            }
            case NORMAL:{
                setCarFlow(normalCarFlow);
                break;
            }
            case EXPONENTIAL:{
                setCarFlow(exponentialCarFlow);
                break;
            }
            default: {
                assert false:"wrong flow type!";
                break;
            }
        }
    }

    private void setCarFlow(CarFlow flow){
        this.carFlow=flow;
        notifyListenersOfFlowChange();
    }

    public Engine(){

        carGenerator = new CarGenerator();
        normalCarFlow = new NormalCarFlow(this);
        exponentialCarFlow = new ExponentialCarFlow(this);
        determinedCarFlow = new DeterminedCarFlow(this);
        uniformCarFlow = new UniformCarFlow(this);

        carFlow = uniformCarFlow;

        timePast=0;

        timeThread = new TimeThread(this);
        timeThread.setDaemon(true);
        timeThread.start();
    }

    public void enableAuto(){
        timeThread.setEnabled(true);
        notifyListenersOfPropertiesChange();
    }
    public void disableAuto(){
        timeThread.setEnabled(false);
        notifyListenersOfPropertiesChange();
    }
    public boolean isAutoEnabled(){
        return timeThread.isEnabled();
    }
    public void setAutoTickTime(int tickTimeInSeconds){
        timeThread.setTickTimeInMilis(tickTimeInSeconds);
        notifyListenersOfPropertiesChange();
    }
    public int getAutoTickTime(){
        return timeThread.getTickTimeInMilis();
    }

    public int getStepTime() {
        return stepTime;
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

    private void selectCar(Car carToSelect) {
        if (selectedCar!=null){
            selectedCar.setSelected(false);
        }
        selectedCar = carToSelect;
        selectedCar.setSelected(true);
    }

    public void selectNextCar(){
        if ( ! no_selected_car ){
            int selected_index = cars.indexOf(selectedCar);
            if (selected_index<cars.size()-1){
                Car newCar = (Car)cars.get(selected_index+1);
                selectCar(newCar);
            }
        }
        notifyListenersOfPropertiesChange();
        notifyListenersOfDataChange();
    }

    public void selectPreviousCar(){
        if ( ! no_selected_car ){
            int selected_index = cars.indexOf(selectedCar);
            if (selected_index>0){
                Car newCar = (Car)cars.get(selected_index-1);
                selectCar(newCar);
            }
        }
        notifyListenersOfPropertiesChange();
        notifyListenersOfDataChange();
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    private void moveAllFromLastToFirst(LinkedList<Movable> movables, int time) {
        LinkedList<Movable> toExcludeFromRoad = new LinkedList<>();
        Movable current;
        if (movables.size()==0) {
            no_selected_car =true;
        }

        for(int i=movables.size()-1; i>=0; i--){
            road.textVisualize(timePast);
            current=movables.get(i);
            if (current.move(time)==false){
                toExcludeFromRoad.add(current);
                if (current instanceof Car && ((Car) current).isSelected()){
                    no_selected_car=true;
                }
            }
            else{
                if (no_selected_car && current instanceof Car){
                    selectCar((Car)current);
                    no_selected_car=false;
                }
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

    public void notifyListenersOfDataChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfDataChange();
        }
    }

    public void notifyListenersOfStructureChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfStructureChange();
        }
    }

    public void notifyListenersOfPropertiesChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfPropertiesChange();
        }
    }

    public void notifyListenersOfFlowChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfFlowChange();
        }
    }

    public void notifyListenersOfCarModelsChange(){
        for(ModelListener listener: listeners){
            listener.notifyOfCarModelsChange();
        }
    }

    public void notifyAllListeners(){
        notifyListenersOfDataChange();
        notifyListenersOfPropertiesChange();
        notifyListenersOfStructureChange();
        notifyListenersOfCarModelsChange();
        notifyListenersOfFlowChange();
    }

    public void setSelectedCarSpeed(int selectedCarSpeed) {
        Car selected = getSelectedCar();
        if (selected!=null){
            selected.setSpeed(selectedCarSpeed);
        }
        notifyListenersOfDataChange();
        notifyListenersOfPropertiesChange();
    }

    public void setAccelerationDivider(int divider) {
        Car.setAccelerationDivider(divider);
        notifyListenersOfPropertiesChange();
    }

    public void setAcceleration(boolean isAccelerationOn){
        Car.setAcceleration(isAccelerationOn);
        notifyListenersOfPropertiesChange();
    }

    public void setRoadLength(int newRoadLength) {
        timePast=0;
        road = new Road(road.getSpeedLimitation(), newRoadLength);
        selectedCar = null;
        cars = new LinkedList<>();
        notifyAllListeners();
    }
}
