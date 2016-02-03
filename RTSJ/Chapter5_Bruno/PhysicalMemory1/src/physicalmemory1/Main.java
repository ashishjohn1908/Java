package physicalmemory1;
import javax.realtime.*;
public class Main extends RealtimeThread {
    class MyClass {
        String name;
        Long tick;
        
        public MyClass() {
           this.name = this.toString();
           this.tick = new Long(System.currentTimeMillis() );
        }
        
        public void info() {
            System.out.println(
                    "Object " + name +
                    " created at tick=" + tick);
        }
    }

    public void run() {
        try {
            LTPhysicalMemory mem = 
                new LTPhysicalMemory(
                    MyClass.class, 10240000);

            MyClass obj = 
                (MyClass)mem.newInstance(MyClass.class);
            
            obj.info();
            
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}
