package Model;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public class Coordinate {
    private boolean isOccupied = false;
    private Movable occupier = null;
    private int xAxis;

    public Coordinate(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getXAxis() {
        return xAxis;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Movable getOccupier() {
        return occupier;
    }

    public void setOccupier(Movable occupier) {
        this.occupier = occupier;
    }
}
