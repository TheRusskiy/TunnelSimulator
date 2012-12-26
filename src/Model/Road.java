package Model;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class Road {
    private Coordinate[] coordinates;
    private int roadLengthInMetres =200;
    private int speedLimitation;
    public Road(int speedLimitation){
        this.speedLimitation=speedLimitation;
        coordinates=new Coordinate[roadLengthInMetres];
        for(int i=0; i<coordinates.length; i++){
            coordinates[i]=new Coordinate(i);
        }
    }
    public Coordinate getStartingCoordinate(){
        return coordinates[0];
    }

    public Coordinate moveBy(Coordinate from, int moveBy, int objectLength){
        //todo implement
         assert false: "implement";

        return null;
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

    public Coordinate nextCoordinate(Coordinate from, int shift){
        int fromIndex=from.getXAxis();
        assert fromIndex+shift<coordinates.length:"Next coordinate is out of bounds!";
        return coordinates[fromIndex+shift];
    }
}
