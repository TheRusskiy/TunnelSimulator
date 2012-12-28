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
public class DeterminedCarFlow extends CarFlow{
    private int secondsEachCarAppears = 10;
    private Random randomGenerator = new Random();

    public DeterminedCarFlow(Engine engine) {
        super(engine);
    }

    @Override
    public boolean hasNextCar(int time) {
        int isThereACarThisSecond = -1;
        for(int i=0; i<time; i++){
            isThereACarThisSecond=randomGenerator.nextInt(secondsEachCarAppears);
            if (isThereACarThisSecond==0) return true; //If zero => Car appears
        }
        return false;
    }
}
