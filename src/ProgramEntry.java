import Controller.TunnelController;
import Model.Engine;
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
        Engine engine = new Engine();
        engine.enableAuto();

        TunnelController controller = new TunnelController(engine);

        TunnelView view = new TunnelView(controller);
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
