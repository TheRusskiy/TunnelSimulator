package Model;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 26.12.12
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class TimeThread extends Thread {
    public static final int MINIMUM_TICK_TIME=50;
    public static final int MAXIMUM_TICK_TIME=10000;

    private int tickTimeInMilis=100;
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

    public void setTickTimeInMilis(int newTimeInMilis) {
        if (newTimeInMilis<MINIMUM_TICK_TIME){
            newTimeInMilis=MINIMUM_TICK_TIME;
        }
        if (newTimeInMilis>MAXIMUM_TICK_TIME){
            newTimeInMilis=MAXIMUM_TICK_TIME;
        }
        tickTimeInMilis=newTimeInMilis;
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

    public int getTickTimeInMilis() {
        return tickTimeInMilis;
    }
}
