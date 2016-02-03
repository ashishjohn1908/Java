// Demonstrate ArrayDeque.

import java.util.*;

class ArrayDequeDemo {
    public static void main(String args[]) {
        // Create a tree set.
        ArrayDeque<String> adq = new ArrayDeque<String>();

        // Use an ArrayDeque like a stack.
        adq.push("A");
        adq.push("B");
        adq.push("D");
        adq.push("E");
        adq.push("F");

        System.out.print("Popping the stack: ");

//    while(adq.peek() != null) {
        //     System.out.print(adq.pop() + " ");
//	}
        System.out.println();

        Iterator itor = adq.iterator();
        while (itor.hasNext()) {
            System.out.print(" " + itor.next());
        }
        System.out.println("");

        Iterator itor1 = adq.descendingIterator();
        while (itor1.hasNext()) {
            System.out.print(" " + itor1.next());
        }
        System.out.println("");

    }
}