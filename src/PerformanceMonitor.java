
import java.util.Timer;

public class PerformanceMonitor {


    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        Killer killer=new Killer();
        timer.schedule(killer,0,1000000);
    }
}