import Controller.TunnelController;
import Model.Engine;
import View.TunnelView;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class ProgramEntry {
    public static void main(String[] args){
        SplashScreen mySplash =SplashScreen.getSplashScreen();
        try
        {
            //FIXME 6000
            Thread.sleep(0);
        }
        catch (InterruptedException ex)
        {}

        Engine engine = new Engine();
//        engine.enableAuto();

        TunnelController controller = new TunnelController(engine);

        TunnelView view = new TunnelView(controller);
    }
}
