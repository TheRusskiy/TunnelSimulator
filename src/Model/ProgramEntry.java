package Model;

import Model.car.CarGenerator;
import Model.carflow.UniformCarFlow;
import View.TunnelView;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class ProgramEntry {
    public static void main(String[] args){
        CarGenerator carGenerator = new CarGenerator();
        Engine engine = new Engine(new UniformCarFlow(carGenerator));
        engine.setStepTime(5);
        engine.setAutoTickTime(1);
        engine.enableAuto();

        TunnelView view = new TunnelView();
//        while (1==1){
//            try {
//                System.out.println("Inside Engine simulation println to be replaced iwth Swing thread");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
