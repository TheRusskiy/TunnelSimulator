package Model.carflow;

import Model.Engine;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class UniformCarFlow extends CarFlow{
    private final static int DEFAULT_PARAMETER_T = 10;
    public final static int MAXIMUM_PARAMETER_T = 1000;
    public final static int MINIMUM_PARAMETER_T = 1;
    private int param_T = DEFAULT_PARAMETER_T; //secondsEachCarAppears
    private Random randomGenerator = new Random();

    public UniformCarFlow(Engine engine) {
        super(engine);
    }


    public int getParam_T() {
        return param_T;
    }

    public void setParam_T(int param_T) {
        if (param_T>MAXIMUM_PARAMETER_T){
            param_T = MAXIMUM_PARAMETER_T;
        }
        if (param_T<MINIMUM_PARAMETER_T){
            param_T = MINIMUM_PARAMETER_T;
        }
        this.param_T = param_T;
        engine.notifyListenersOfFlowChange();
    }

    @Override
    public boolean hasNextCar(int time) {
        int isThereACarThisSecond = -1;
        for(int i=0; i<time; i++){
            isThereACarThisSecond=randomGenerator.nextInt(param_T);
            if (isThereACarThisSecond==0) return true; //If zero => Car appears
        }
        return false;
    }
}
