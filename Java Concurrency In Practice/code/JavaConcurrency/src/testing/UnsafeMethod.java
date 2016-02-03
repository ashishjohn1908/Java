package testing;

import net.jcip.examples.UnsafeSequence;
import net.jcip.examples.Sequence;



/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 19-Nov-2009
 * Time: 13:34:41
 * To change this template use File | Settings | File Templates.
 */
public class UnsafeMethod implements Runnable{
    UnsafeSequence sequence = new UnsafeSequence();
    Sequence seq = new Sequence();
    public void run() {
        System.out.print(sequence.getNext());
        System.out.print(" : ");
        System.out.println(seq.getNext());
    }

    private UnsafeMethod(){
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
        new Thread(this).start();
    }

    
    public static void main(String[] args) {
        new UnsafeMethod();

    }

}
