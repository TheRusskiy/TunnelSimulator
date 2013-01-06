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
        try
        {
            //FIXME 6000
            SplashScreen mySplash =SplashScreen.getSplashScreen();
            if (mySplash==null) throw new Exception("Splash can't be loaded");
            Thread.sleep(1000);
        }
        catch (Exception ex)
        {}

        Engine engine = new Engine();
//        engine.enableAuto();

        TunnelController controller = new TunnelController(engine);

        TunnelView view = new TunnelView(controller);
    }
}
