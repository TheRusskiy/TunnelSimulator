package View;

import Controller.ModelListener;
import Controller.TunnelController;
import Model.Coordinate;
import Model.Engine;
import Model.car.Car;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 3:26
 * To change this template use File | Settings | File Templates.
 */
public class VisualPanel extends JPanel implements ModelListener {
    private Dimension preferredSize = new Dimension(100, 100);
    private static final int MAXIMUM_SCALE = 8;
    private static final int MINIMUM_SCALE = 1;
    private TunnelController controller;
    private Coordinate[] coordinates;
    private Graphics2D g;
    private int meter = 4; //px
    private Color roadColor = new Color(160, 160, 160);
    private Color rulerColor = new Color(0, 0, 0);
    private Color selectedCarColor = new Color(255, 23, 0);
    private Color normalCarColor = new Color(0, 0, 0);
    private Color timeColor = new Color(0, 0, 0);
    private Font font;
    private TunnelView tunnelView;
    private Engine engine;



    enum Spaces {
        LEFT_MARGIN(5),
        RIGHT_MARGIN(5),
        TOP_MARGIN(20),
        BOTTOM_MARGIN(5),
        ROAD_HEIGHT(6),
        RULER_STEP(20),
        RULER_BOTTOM(4),
        CAR_HEIGHT(3);
        public int meters;
        Spaces(int length_in_metres){
            this.meters =length_in_metres;
        }
    }


    public VisualPanel(TunnelController controller, TunnelView tunnelView){
        this.tunnelView=tunnelView;
        this.controller=controller;
        this.controller.setVisualPanel(this);
        this.engine=controller.getEngine();
        controller.registerListener(this);
        coordinates=engine.getRoad().getCoordinates();
        calculateMargins();
    }

    private int totalWidth;
    private int totalHeight;
    private int roadLength;
    private int roadHeight;
    private int roadX0;
    private int roadY0;
    private int rulerY0;
    private int rulerY1;
    private int rulerXStart;
    private int rulerXStep;
    private int rulerXEnd;
    private int carXShift;
    private int carY0;
    private int speedLineY0;
    private int speedLineY1;
    private int speedXShift;

    private void calculateMargins() {

        roadX0=Spaces.LEFT_MARGIN.meters* meter;
        roadY0=Spaces.TOP_MARGIN.meters* meter;
        roadLength=coordinates.length* meter;
        roadHeight=Spaces.ROAD_HEIGHT.meters* meter;

        totalWidth = roadLength+(Spaces.LEFT_MARGIN.meters+ Spaces.RIGHT_MARGIN.meters)* meter;
        totalHeight = (Spaces.TOP_MARGIN.meters+ Spaces.BOTTOM_MARGIN.meters+ Spaces.ROAD_HEIGHT.meters)* meter;

        rulerY0 = Spaces.TOP_MARGIN.meters* meter;
        rulerY1 = (Spaces.TOP_MARGIN.meters+Spaces.ROAD_HEIGHT.meters+Spaces.RULER_BOTTOM.meters)* meter;
        rulerXStart = Spaces.LEFT_MARGIN.meters* meter;
        rulerXStep = Spaces.RULER_STEP.meters* meter;
        rulerXEnd = rulerXStart+roadLength;

        carY0 = (Spaces.TOP_MARGIN.meters+(Spaces.ROAD_HEIGHT.meters-Spaces.CAR_HEIGHT.meters)/2)* meter;
        carXShift = (Spaces.LEFT_MARGIN.meters-Car.CAR_LENGTH)* meter;

        speedLineY0=Spaces.TOP_MARGIN.meters/2*meter;
        speedLineY1=carY0;
        speedXShift=carXShift+Car.CAR_LENGTH/2*meter;

        setSize();
        font = new Font("Arial", Font.PLAIN, new Double(Math.sqrt(meter)*6).intValue());

    }

    private void setSize(){
        Dimension sizeDimension = new Dimension(totalWidth, totalHeight);
        this.setSize(sizeDimension);
        this.setMinimumSize(sizeDimension);
        this.setMaximumSize(sizeDimension);
        preferredSize = new Dimension(sizeDimension);
        tunnelView.updateSize();
    }

    public void paint(Graphics graphics){
        super.paint(graphics);
        g = (Graphics2D) graphics;
        g.setFont(font);
        drawRoad();
        drawRuler();
        drawCars();
        drawTime();
        //g.drawLine(10, 10, 150, 150);
    }

    public void update(Graphics graphics){
        super.update(graphics);
    }

    private void drawRoad() {
        g.setColor(roadColor);
        g.fillRect(roadX0, roadY0, roadLength, roadHeight);
    }

    private void drawRuler() {
        g.setColor(rulerColor);
        int currRuler = rulerXStart;
        int currText = 0;
        while(currRuler<=rulerXEnd){
            g.drawLine(currRuler, rulerY0, currRuler, rulerY1);
            g.drawString(Integer.toString(currText), currRuler, rulerY1);
            currRuler+=rulerXStep;
            currText+=Spaces.RULER_STEP.meters;
        }
    }

    private void drawCars() {
        Car car;
        int fromStart;
        g.setColor(normalCarColor);
        for(Coordinate coordinate:coordinates){
            if (coordinate.getOccupier()!=null){
                car = (Car)coordinate.getOccupier();
                fromStart=coordinate.getXAxis()*meter;
                g.drawImage(car.getIcon().getImage(), carXShift+fromStart, carY0, null);
                if (car.isSelected()) {
                    g.setColor(selectedCarColor);
                }else{
                    g.setColor(normalCarColor);
                }
                g.drawLine(speedXShift+fromStart, speedLineY0,speedXShift+fromStart, speedLineY1);
                g.drawString(car.getSpeed()+"", speedXShift+fromStart-meter*2, speedLineY0);
            }
        }
    }

    private void drawTime(){
        g.setColor(timeColor);
        g.drawString("Time past: " + engine.getTimePast() + "s", 0, 0 + meter * 3);
    }


    public Dimension getPreferredSize() {
        return this.preferredSize;
    }

    @Override
    public void notifyOfDataChange() {
        refreshTunnel();
    }

    @Override
    public void notifyOfPropertiesChange() {

    }

    @Override
    public void notifyOfStructureChange() {

    }

    public void refreshTunnel() {
        this.repaint();
    }

    public int getScale(){
        return meter;
    }

    public void setScale(int pixelsInMeter){
        if (pixelsInMeter>MAXIMUM_SCALE){
            pixelsInMeter=MAXIMUM_SCALE;
        }
        if (pixelsInMeter<MINIMUM_SCALE){
            pixelsInMeter=MINIMUM_SCALE;
        }
        meter=pixelsInMeter;
        calculateMargins();
    }
}
