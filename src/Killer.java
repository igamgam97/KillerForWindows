import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.TimerTask;

public class Killer extends TimerTask {

    public void run() {
        final Sigar sigar;
        long CPUOfCandidate = Long.MAX_VALUE;
        long MemOfCandidate = 0;
        long[] listOfPID;
        long PIDOfCandidate = 0;
        try {
            if((new Sigar().getMem().getUsedPercent()<90.0)) {
                sigar = new Sigar();
                listOfPID = sigar.getProcList();
                for (long pid : listOfPID) {
                    if ((sigar.getProcCpu(pid).getTotal() < CPUOfCandidate) && (sigar.getProcMem(pid).getSize() > MemOfCandidate)) {
                        CPUOfCandidate = sigar.getProcCpu(pid).getTotal();
                        MemOfCandidate = sigar.getProcMem(pid).getSize();
                        PIDOfCandidate = pid;


                    }
                }
                sigar.kill(PIDOfCandidate, -9);
            }

        } catch (SigarException e) {
            e.printStackTrace();
        }
    }
}
