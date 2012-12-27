package Controller;

import Model.Engine;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class TunnelController {
    Engine engine;

    public TunnelController(Engine engine) {
        this.engine = engine;
    }

    public void registerListener(ModelListener listener){
        engine.registerListener(listener);
    }
}
