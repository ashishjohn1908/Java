package multiscopeobject;

import java.util.*;
import javax.realtime.*;
public class Main extends RealtimeThread {
    
    public Main(MemoryArea mem) {
        super(null, null, null, mem, null, null);
        
        ReleaseParameters rel = 
          new PeriodicParameters(
            new RelativeTime(1,0 )); // 1 milli
        
        this.setReleaseParameters(rel);
    }

    class TemperatureData {
        private Vector data = new Vector();
        public Vector getData() {
            return data;
        }
        class SafePut implements Runnable {
            String val;
            SafePut( String v) {
                val = new String(v);
            }
            
            public void run() {
                String v = cloneString(val);
                data.addElement( v );
                //System.out.println("Added: "+v+", Count="+data.size());
            }
            
            private String cloneString(String s) {
                try {
                    String r = (String)getMemoryArea().newInstance(String.class);
                    r = r.copyValueOf(s.toCharArray());
                    return r;
                }
                catch ( Exception e ) { 
                    e.printStackTrace();
                }
                return null;
            }
        }
        
        void update(String val) {
            MemoryArea mem = MemoryArea.getMemoryArea(this);
            mem.executeInArea( new SafePut(val) );
        }
    }
    
    class TempGauge implements Runnable {
        public TemperatureData history;
        public void run() {
            MemoryArea mem = RealtimeThread.getCurrentMemoryArea();
            System.out.println("Running logic in:\t" + mem.toString());
            String v = "100";
            history.update(v);
        }
    }
    
    static ScopedMemory smMain = new LTMemory(4096000); // ~4MB
    static ScopedMemory smGauge = new LTMemory(4096000); // ~4MB
    
    public void run() {
        TemperatureData temp = new TemperatureData();
        System.out.println("TemperatureData in:\t" + 
                MemoryArea.getMemoryArea(temp));
        
        TempGauge gauge = new TempGauge();
        gauge.history = temp;
        
        int times = 0;
        while ( times++ < 10 ) {
            smGauge.enter(gauge);
            waitForNextPeriod();
        }
        
        Vector data = temp.getData();
        for (int i = 0; i < data.size(); i++) {
            String v = (String)data.elementAt(i);
            System.out.println("Val=" + v);
        }
    }

    public static void main(String[] args) {
        Main main = new Main(smMain);
        main.start();
    }

}
