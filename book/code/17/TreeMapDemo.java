import java.util.*;

class TreeMapDemo {
    public static void main(String args[]) {

        // Create a tree map.
        SortedMap<String, Double> tm = new TreeMap<String, Double>();

        System.out.println("Sorted Map");

        // Put elements to the map.
        tm.put("John Doe", new Double(3434.34));
        tm.put("Tom Smith", new Double(123.22));
        tm.put("Jane Baker", new Double(1378.00));
        tm.put("Tod Hall", new Double(99.22));
        tm.put("Ralph Smith", new Double(-19.08));

        // Get a set of the entries.
        Set<Map.Entry<String, Double>> set = tm.entrySet();

        // Display the elements.
        for (Map.Entry<String, Double> me : set) {
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println();

        System.out.println("Linked Hsash Map");
        // Create a tree map.
        LinkedHashMap<String, Double> tml = new LinkedHashMap<String, Double>();

        // Put elements to the map.
        tml.put("John Doe", new Double(3434.34));
        tml.put("Tom Smith", new Double(123.22));
        tml.put("Jane Baker", new Double(1378.00));
        tml.put("Tod Hall", new Double(99.22));
        tml.put("Ralph Smith", new Double(-19.08));

        // Get a set of the entries.
        Set<Map.Entry<String, Double>> setl = tml.entrySet();

        // Display the elements.
        for (Map.Entry<String, Double> me : setl) {
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }

        System.out.println();  // Deposit 1000 into John Doe's account.
        double balance = tm.get("John Doe");
        tm.put("John Doe", balance + 1000);

        System.out.println("John Doe's new balance: " + tm.get("John Doe"));
    }
}
