package Model;

import Model.car.Car;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class Road {
    public static final int MINIMUM_SPEED_LIMIT=5;
    public static final int MAXIMUM_SPEED_LIMIT=300;
    public static final int MINIMUM_LENGTH=200;
    public static final int MAXIMUM_LENGTH=2000;
    public static final int BETWEEN_CAR_GAP=1;
    private Coordinate[] coordinates;
    private volatile int speedLimitation;

    public Road(int speedLimitation, int roadLengthInMetres){
        if (roadLengthInMetres>MAXIMUM_LENGTH){
            roadLengthInMetres=MAXIMUM_LENGTH;
        }
        if (roadLengthInMetres<MINIMUM_LENGTH){
            roadLengthInMetres=MINIMUM_LENGTH;
        }
        setSpeedLimitation(speedLimitation);
        coordinates=new Coordinate[roadLengthInMetres];
        for(int i=0; i<coordinates.length; i++){
            coordinates[i]=new Coordinate(i);
        }
    }

    public Coordinate[] getCoordinates() {
        assert coordinates!=null:"Coordinates == null!";
        return coordinates;
    }

    public Coordinate getStartingCoordinate(){
        return coordinates[0];
    }

    public Coordinate getLastCoordinate(){
        return coordinates[coordinates.length-1];
    }

    public int getSpeedLimitation() {
        return speedLimitation;
    }

    public void setSpeedLimitation(int speedLimitation) {
        if (speedLimitation>MAXIMUM_SPEED_LIMIT){
            speedLimitation=MAXIMUM_SPEED_LIMIT;
        }
        if (speedLimitation<MINIMUM_SPEED_LIMIT){
            speedLimitation=MINIMUM_SPEED_LIMIT;
        }
        this.speedLimitation = speedLimitation;
    }


    /**
     * @return NULL if moves beyond coordinates
     */
    public Coordinate moveBy(Coordinate from, int moveBy, int objectLength){
        objectLength+=BETWEEN_CAR_GAP;
        int fromIndex=from.getXAxis();
        int canMoveCoordinateCount = 0;
        for(int i=1; i<= moveBy; i++){
            if ((fromIndex+i>=coordinates.length)||(coordinates[fromIndex+i].isOccupied())) break;
            canMoveCoordinateCount=i;
        }
        if (canMoveCoordinateCount!=0){
            setOccupation(fromIndex-objectLength+1, fromIndex, false); //clear occupation
            textVisualize(0);
            Coordinate newCoordinate = nextCoordinate(from, canMoveCoordinateCount);

            newCoordinate.setOccupier(from.getOccupier());
            if (newCoordinate.getXAxis()!=from.getXAxis()){
                from.setOccupier(null);
            }
            setOccupation(fromIndex - objectLength + canMoveCoordinateCount + 1, fromIndex + canMoveCoordinateCount, true); //set occupation
            textVisualize(0);
            return newCoordinate;
        }
        else{ //If can't move
            //check if you are at the end
            if ((moveBy!=0)&&(from==getLastCoordinate())){
                setOccupation(fromIndex-objectLength+1, fromIndex, false); //clear occupation
                from.setOccupier(null);
                return null;
            }
            else{
            //not end, just a car in front of you, do nothing
            textVisualize(0);
            return from;
            }
        }
    }

    public boolean isOccupied(Coordinate from, int length){
        int fromPosition =from.getXAxis();
        for(int j=0; j<length; j++){
            if (coordinates[fromPosition+j].isOccupied()) return true;
        }
        return false;
    }
    public boolean isNotOccupied(Coordinate from, int length){
        return !isOccupied(from, length);
    }

    /**
     * @return NULL if moves beyond coordinates
     */
    public Coordinate nextCoordinate(Coordinate from, int shift){
        int fromIndex=from.getXAxis();
        if ((fromIndex+shift)>=coordinates.length) return null ;
        return coordinates[fromIndex+shift];
    }

    public void setOccupation(int from, int toInclusive, boolean isOccupied){
        if (from<0){
            if (Engine.DEBUG_MODE) System.err.println("WARNING: From is < 0, setting to 0!");
            from=0;
        }
        if (toInclusive>=coordinates.length){
            toInclusive=coordinates.length-1;
        }
        for(int i=from; i<=toInclusive; i++){
            coordinates[i].setOccupied(isOccupied);
        }
    }

    public void textVisualize(int timePast){
        try {
            if (!Engine.DEBUG_MODE) return;
            FileWriter fileWriter = new FileWriter("F:\\cars.txt");
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.println("Time past: "+timePast);
            for(Coordinate c: coordinates){
                pw.printf("%03d",c.getXAxis());
                if (c.isOccupied()){
                    pw.printf(": "+c.isOccupied());
                }
                if (c.getOccupier()!=null){
                    pw.printf(": "+((Car)c.getOccupier()).getSpeed());
                }
                pw.println("");
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRoadLength() {
        return coordinates.length;
    }

    public static int getDistanceBetween(Coordinate coordinate_1, Coordinate coordinate_2){
        if (coordinate_1==null || coordinate_2==null){
            assert false: "can't compare zero coordinates!";
            return 0;
        }
        else{
            return Math.abs(coordinate_1.getXAxis()-coordinate_2.getXAxis());
        }
    }
}
