package Model.carflow;

import Model.Engine;
import cern.jet.random.Exponential;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class ExponentialCarFlow extends CarFlow{
    private final static int DEFAULT_PARAMETER_T = 20;
    public final static int MAXIMUM_PARAMETER_T = 100;
    public final static int MINIMUM_PARAMETER_T = 1;
    private int param_T = DEFAULT_PARAMETER_T;
    //private Random randomGenerator = new Random();
    private double lambda;
    private double accumulator=0;
    private RandomEngine randomEngine;
    private Exponential exponential;

    /**
     * p(x) = lambda*exp(-x*lambda) for x >= 0, lambda > 0.
     * http://acs.lbl.gov/software/colt/api/cern/jet/random/Exponential.html
     * http://en.wikipedia.org/wiki/Exponential_distribution
     * Пример. Пусть есть магазин, в который время от времени заходят покупатели.
     * При определённых допущениях время между появлениями двух последовательных
     * покупателей будет случайной величиной с экспоненциальным распределением.
     * Среднее время ожидания нового покупателя равно 1/lambda.
     * Сам параметр lambda тогда может быть интерпретирован,
     * как среднее число новых покупателей за единицу времени.
     */
    public ExponentialCarFlow(Engine engine) {
        super(engine);
        randomEngine = new DRand();
        exponential = new Exponential(getParam_lambda(), randomEngine);
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
        randomEngine = new DRand();
        exponential = new Exponential(getParam_lambda(), randomEngine);
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
//        lambda = 1.0/param_T;
//
//        accumulator+= -1 * Math.log(1 - randomGenerator.nextDouble()) / lambda;
//        if (accumulator>=100){
//            accumulator-=100;
//            if (engine.DEBUG_MODE) System.out.println("1+"+accumulator);
//            return true;
//        }
//        else{
//            if (engine.DEBUG_MODE) System.out.println("0+"+accumulator);
//            return false;
//        }
//
        Double d = exponential.nextDouble();
        if (engine.DEBUG_MODE) System.out.println(d);
        accumulator+=d;
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
