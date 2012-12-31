package Model.car;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public class CarGenerator {
    private CarModelsList models;
    private Random randomModelGenerator;
    private Random randomSpeedGenerator;

    public  CarGenerator(){
        models=loadCarModels();
        randomModelGenerator = new Random();
        randomSpeedGenerator = new Random();
    }

    private CarModelsList loadCarModels() {
        //TODO replace this stub
        /*
        it should load models from a file
         */
        CarModel stubModel = new CarModel(16, "StubCar", new CarIcon(100, 100, 100));
        CarModelsList newModelsList = new CarModelsList();
        newModelsList.putModel(stubModel);
        return newModelsList;
    }

    public CarModelsList getModels() {
        return models;
    }

    public Car createCar(){
        assert models.size()>0 :"Zero models in a list!";

        int modelIndex = randomModelGenerator.nextInt(models.size());
        int newCarMaxSpeed = models.get(modelIndex).getMaxSpeed();
        int newCarSpeed = randomSpeedGenerator.nextInt(newCarMaxSpeed);
        if (newCarSpeed<1) newCarSpeed=1;
        Car createdCar = new Car(models.get(modelIndex),newCarSpeed );
        return createdCar;
    }
}
