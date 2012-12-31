package Model.car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 31.12.12
 * Time: 0:13
 * To change this template use File | Settings | File Templates.
 */
public class CarModelsList implements Iterable<CarModel>, Serializable {
    private List<CarModel> carModelList;
    public CarModelsList(List<CarModel> carModels) {
        assert (carModels!=null): "Car models list constructor was passed null";
        carModelList = carModels;
    }

    public CarModelsList(){
        carModelList = new ArrayList<>();
    }
    @Override
    public Iterator<CarModel> iterator() {
        return carModelList.iterator();
    }

    public void putModel(CarModel model){
        if (model!=null){
            carModelList.add(model);
        }
        else{
            //Do nothing?
            System.err.println("CarModelsList received NULL model");
        }
    }
    public void replaceModels(CarModelsList newModels){
        carModelList=newModels.carModelList;
    }

    public int size(){
        return carModelList.size();
    }

    public CarModel get(int index){
        return carModelList.get(index);
    }

    public void remove(CarModel modelToRemove){
        int toDelete = carModelList.indexOf(modelToRemove);
        if (toDelete==-1){
            System.err.println("Model to delete isn't in a list!");
            return;
        }
        ArrayList<CarModel> newCarModels = new ArrayList<>();
        newCarModels.addAll(carModelList.subList(0, toDelete));
        newCarModels.addAll(carModelList.subList(toDelete+1, carModelList.size()));
        carModelList=newCarModels;
    }
}
