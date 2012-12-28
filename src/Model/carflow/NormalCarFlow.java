package Model.carflow;

import Model.Engine;
import cern.jet.random.Normal;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class NormalCarFlow extends CarFlow{
    private final static int DEFAULT_PARAMETER_M = 50;
    public final static int MAXIMUM_PARAMETER_M = 100;
    public final static int MINIMUM_PARAMETER_M = 0;
    private int param_M = DEFAULT_PARAMETER_M;
    private final static int DEFAULT_PARAMETER_D = 10;
    public final static int MAXIMUM_PARAMETER_D = 100;
    public final static int MINIMUM_PARAMETER_D = 0;
    private int param_D = DEFAULT_PARAMETER_D;
    RandomEngine randomEngine;
    private double accumulator=0;
    private Normal normal;

    public NormalCarFlow(Engine engine) {
        super(engine);
        randomEngine = new DRand();
        normal = new Normal(((double)param_M)/100, ((double)param_D)/100, randomEngine);
    }


    public int getParam_M() {
        return param_M;
    }

    public int getParam_D() {
        return param_D;
    }

    public void setParam_M(int param_M) {
        if (param_M > MAXIMUM_PARAMETER_M){
            param_M = MAXIMUM_PARAMETER_M;
        }
        if (param_M < MINIMUM_PARAMETER_M){
            param_M = MINIMUM_PARAMETER_M;
        }
        this.param_M = param_M;
        accumulator=0;
        randomEngine = new DRand();
        normal = new Normal(((double)param_M)/100, ((double)param_D)/100, randomEngine);
        engine.notifyListenersOfFlowChange();
    }

    public void setParam_D(int param_D) {
        if (param_D > MAXIMUM_PARAMETER_D){
            param_D = MAXIMUM_PARAMETER_D;
        }
        if (param_D < MINIMUM_PARAMETER_D){
            param_D = MINIMUM_PARAMETER_D;
        }
        this.param_D = param_D;
        accumulator=0;
        randomEngine = new DRand();
        normal = new Normal(((double)param_M)/100, ((double)param_D)/100, randomEngine);
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
        double d = normal.nextDouble();
        accumulator+=d;
        if (accumulator>=1){
            accumulator-=1;
            if (engine.DEBUG_MODE) System.out.println("1+"+accumulator);
            return true;
        }
        else{
            if (engine.DEBUG_MODE) System.out.println("0+"+accumulator);
            return false;
        }
    }
}
