package Controller;

import Model.Engine;
import View.Controls.VisualPanel;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class TunnelController {
    private Engine engine;
    private VisualPanel visualPanel;

    public Engine getEngine() {
        return engine;
    }

    public TunnelController(Engine engine) {
        this.engine = engine;
    }

    public void setVisualPanel(VisualPanel visualPanel) {
        this.visualPanel = visualPanel;
    }

    public void registerListener(ModelListener listener){
        engine.registerListener(listener);
    }

    public void askForNotify(){
        engine.notifyAllListeners();
    }

    public int getScale() {
        return visualPanel.getScale();
    }

    public void autoOn() {
        engine.enableAuto();
    }

    public void autoOff() {
        engine.disableAuto();
    }

    public void nextStep() {
        engine.step();
    }

    public void changeAutoDelay(String delayInMiliseconds) {
        int digital=new Integer(delayInMiliseconds);
        engine.setAutoTickTime(digital);
    }


    public void changeStepTimeAndVMax(String stepTimeInSeconds, String roadSpeedLimit) {
        int digital=new Integer(stepTimeInSeconds);
        engine.setStepTime(digital);

        digital=new Integer(roadSpeedLimit);
        engine.setSpeedLimit(digital);
    }

    public void previousCar() {
        engine.selectPreviousCar();
    }

    public void nextCar() {
        engine.selectNextCar();
    }

    public void changeSelectedCarSpeed(String newSpeed) {
        int digital=new Integer(newSpeed);
        engine.setSelectedCarSpeed(digital);
    }

    public void changeRoadLengthAndScale(String roadLength, String roadScale) {
        int digital=new Integer(roadLength);
        engine.setRoadLength(digital);
        digital=new Integer(roadScale);
        visualPanel.setScale(digital);

    }

    public void changeUniformT(String newT) {
        int digital=new Integer(newT);
        engine.getUniformCarFlow().setParam_T(digital);
    }

    public void changeExponentialT(String newT) {
        int digital=new Integer(newT);
        engine.getExponentialCarFlow().setParam_T(digital);
    }

    public void changeCarFlow(Engine.CarFlows flow_enum){
        engine.setCarFlow(flow_enum);
    }

    public void changeNormalMandD(String param_M, String param_D) {
        int digital=new Integer(param_M);
        engine.getNormalCarFlow().setParam_M(digital);
        digital=new Integer(param_D);
        engine.getNormalCarFlow().setParam_D(digital);
    }

    public void changeDeterminedT(String newT) {
        int digital=new Integer(newT);
        engine.getDeterminedCarFlow().setParam_T(digital);
    }
}
