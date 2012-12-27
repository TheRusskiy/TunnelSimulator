package Model;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class TimeThread extends Thread {
    private int tickTimeInMilis=0;
    private Engine engine;
    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public TimeThread(Engine engine){
        assert engine!=null: "Engine was zero in time constructor!";
        this.engine=engine;
    }

    public void setTickTimeInSeconds(double newTimeInSeconds) {
        assert newTimeInSeconds>0 : "Tick time has to be positive";
        tickTimeInMilis = new Double(newTimeInSeconds*1000).intValue();
    }

    @Override
    public void run() {
        while (true){
        try {
                if(enabled) engine.step();
                Thread.sleep(tickTimeInMilis);
            } catch (InterruptedException e) {
                e.printStackTrace();
                assert false: "Time thread was interrupted";
            }
        }
    }
}
