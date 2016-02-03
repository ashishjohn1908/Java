package plamen;

/**
 * Created by plamen on 27/06/2014.
 */
public class BoleanDemo {

    public static void main(String[] args) {
        boolean myLock = false;
        boolean yourLock = false;

        if (!(myLock && yourLock)){
            if(myLock){
                System.out.printf(String.valueOf(myLock));
            }
            System.out.printf(String.valueOf(myLock));
        }
    }
}
