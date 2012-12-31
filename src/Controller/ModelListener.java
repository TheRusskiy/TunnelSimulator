package Controller;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 27.12.12
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public interface ModelListener {
    public void notifyOfDataChange();
    public void notifyOfPropertiesChange();
    public void notifyOfStructureChange();
    public void notifyOfFlowChange();
    public void notifyOfCarModelsChange();
}
