// Demonstrate various Vector operations. 

import java.util.*;

class VectorDemo {
    public static void main(String args[]) {

        // initial size is 3, increment is 2
        Vector<Integer> v = new Vector<Integer>(3, 2);

        System.out.println("Initial size: " + v.size());
        System.out.println("Initial capacity: " + v.capacity());

        v.addElement(1);
        v.addElement(2);
        v.addElement(3);
        v.addElement(4);

        System.out.println("Capacity after four additions: " + v.capacity());
        v.addElement(5);
        System.out.println("Current capacity: " + v.capacity());
        v.addElement(6);
        v.addElement(7);

        System.out.println("Current capacity: " + v.capacity());
        v.addElement(9);
        v.addElement(10);

        System.out.println("Current capacity: " + v.capacity());
        v.addElement(11);
        v.addElement(12);
        System.out.println("Current capacity: v.addElement(12); " + v.capacity());

        System.out.println("First element: " + v.firstElement());
        System.out.println("Last element: " + v.lastElement());

        if (v.contains(3)) {
            System.out.println("Vector contains 3.");
        }
        // Enumerate the elements in the vector.
        Enumeration vEtion = v.elements();
        Iterator<Integer> iterator = v.iterator();

        System.out.println("\nElements in vector:");

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        // test is done
        /*
          while(vEtion.hasMoreElements()) {
            System.out.print(vEtion.nextElement() + " ");
          }
        */

        // for(Integer intger : v){
        //	System.out.print(intger + " ");
        //}
        System.out.println();
    }
}