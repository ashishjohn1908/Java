/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package immortalanoninner;

import javax.realtime.*;
public class Main extends RealtimeThread {
    public void run() {
        System.out.println("Current memory area = " + getMemoryArea());
        ImmortalMemory.instance().executeInArea(
            new Runnable() {
                public void run() {
                    Object obj = new Object();
                    System.out.println("'obj' allocated in memory area = " + MemoryArea.getMemoryArea(obj));
                }
            }
        );
    }
    class Test implements Runnable {
        public void run() {
            Object obj = new Object();
            System.out.println("Test in " + MemoryArea.getMemoryArea(obj));
        }
    }
    
    public Main() {
        Test t = new Test();
        ImmortalMemory.instance().executeInArea(t);
    }

    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.start();
    }

}
