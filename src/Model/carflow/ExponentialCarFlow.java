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
public class ExponentialCarFlow extends CarFlow{
    private final static int DEFAULT_PARAMETER_T = 40;
    public final static int MAXIMUM_PARAMETER_T = 100;
    public final static int MINIMUM_PARAMETER_T = 1;
    private int param_T = DEFAULT_PARAMETER_T; //secondsEachCarAppears
    private Random randomGenerator = new Random();
    private double lambda;
    private double accumulator=0;

    public ExponentialCarFlow(Engine engine) {
        super(engine);
    }


    public int getParam_T() {
        return param_T;
    }

    public double getParam_lambda() {
        lambda=1.0/param_T;
        return lambda;
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
        lambda = 1.0/param_T;

        accumulator+= -1 * Math.log(1 - randomGenerator.nextDouble()) / lambda;
        if (accumulator>=100){
            accumulator-=100;
            if (engine.DEBUG_MODE) System.out.println("1+"+accumulator);
            return true;
        }
        else{
            if (engine.DEBUG_MODE) System.out.println("0+"+accumulator);
            return false;
        }
    }
}
