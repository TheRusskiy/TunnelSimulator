package Model.carflow;

import Model.Engine;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class DeterminedCarFlow extends CarFlow{
    private final static int DEFAULT_PARAMETER_T = 30;
    public final static int MAXIMUM_PARAMETER_T = 1000;
    public final static int MINIMUM_PARAMETER_T = 1;
    private int param_T = DEFAULT_PARAMETER_T;
    private double accumulator=0;

    public DeterminedCarFlow(Engine engine) {
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
        accumulator=0;
        engine.notifyListenersOfFlowChange();
    }

    @Override
    public boolean hasNextCar(int time) {
        for(int i=0; i<time; i++){
            if (hasNextCar()) return true;
        }
        return false;
    }
    private boolean hasNextCar(){

        accumulator+=1;
        if (accumulator>=param_T){
            accumulator-=param_T;
            if (engine.DEBUG_MODE) System.out.println("1+"+accumulator);
            return true;
        }
        else{
            if (engine.DEBUG_MODE) System.out.println("0+"+accumulator);
            return false;
        }
    }
}
