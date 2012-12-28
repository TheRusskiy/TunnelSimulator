package Model.carflow;

import cern.jet.random.Poisson;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 28.12.12
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class flowTest {
    private static double lambda=1.0/100;

    public static void main(String[] args){
        Random r = new Random();

        RandomEngine engine = new DRand();
        Poisson poisson = new Poisson(lambda, engine);
//        System.out.println(poisson.pdf(2));

//        int poissonObs;
//        for(int i=0, j=0; i<1000; i++, j++) {
//
//            if (j==40)  {
//                System.out.println();
//                j=0;
//            }
//            System.out.print(poisson.nextInt()+ " ");
//        }

//        def nextTime(rateParameter):
//        return -math.log(1.0 - random.random()) / rateParameter


        for(int i=0, j=0; i<1000; i++, j++) {
            double d = -1 * Math.log(1 - r.nextDouble()) / lambda;
            if (j==40)  {
                System.out.println();
                j=0;
            }
            System.out.print(d+ " ");
        }

    }
}
