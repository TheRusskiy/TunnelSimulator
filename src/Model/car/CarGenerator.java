package Model.car;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public class CarGenerator {
    private LinkedList<CarModel> models;
    private Random randomModelGenerator;
    private Random randomSpeedGenerator;

    public  CarGenerator(){
        models=loadCarModels();
        randomModelGenerator = new Random();
        randomSpeedGenerator = new Random();
    }

    private LinkedList<CarModel> loadCarModels() {
        //TODO replace this stub
        /*
        it should load models from a file
         */
        LinkedList<CarModel>  loadedModels = new LinkedList<>();
        CarModel stubModel = new CarModel(16, "StubCar", new CarIcon());
        loadedModels.add(stubModel);
        return loadedModels;
    }

    public Car createCar(){
        assert models.size()>0 :"Zero models in a list!";
        int modelIndex = randomModelGenerator.nextInt(models.size());
        int newCarMaxSpeed = models.get(modelIndex).getMaxSpeed();
        int newCarSpeed = randomSpeedGenerator.nextInt(newCarMaxSpeed-Car.MINIMUM_SPEED)+Car.MINIMUM_SPEED;
        Car createdCar = new Car(models.get(modelIndex),newCarSpeed );
        return createdCar;
    }
}
