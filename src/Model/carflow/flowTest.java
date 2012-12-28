package Model.carflow;

import cern.jet.random.Normal;
import cern.jet.random.Poisson;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;

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

        RandomEngine randomEngine= new DRand();
        Poisson poisson = new Poisson(lambda, randomEngine);
        int poissonObs = poisson.nextInt();

        double mean = 0.5;
        double variance = 0.0;
        Normal normal = new Normal(mean, variance, randomEngine);
        //double normalObs = normal.nextDouble();


        for(int i=0, j=0; i<1000; i++, j++) {
            double d = normal.nextDouble();
            if (j==40)  {
                System.out.println();
                j=0;
            }
            System.out.print(d+ " ");
        }

    }
}
