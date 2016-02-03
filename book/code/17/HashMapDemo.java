import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

class HashMapDemo {
    public static void main(String args[]) {

        // Create a hash map.
        Map<String, Double> hm = new HashMap<String, Double>();

        // Put elements to the map
        hm.put("John Doe", new Double(3434.34));
        hm.put("Tom Smith", new Double(123.22));
        hm.put("Jane Baker", new Double(1378.00));
        hm.put("Tod Hall", new Double(99.22));
        hm.put("Ralph Smith", new Double(-19.08));
        hm.put("Tod Hall", new Double(99.21));

        // Get a set of the entries.
        Set<Map.Entry<String, Double>> set = hm.entrySet();

        // Display the set.
        for (Map.Entry<String, Double> me : set) {
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println("");
        System.out.println("Linked Hash Map");
        System.out.println("");

        // Create a hash map.
        Map<String, Double> lhm = new LinkedHashMap<String, Double>();

        // Put elements to the map
        lhm.put("John Doe", new Double(3434.34));
        lhm.put("Tom Smith", new Double(123.22));
        lhm.put("Jane Baker", new Double(1378.00));
        lhm.put("Tod Hall", new Double(99.22));
        lhm.put("Ralph Smith", new Double(-19.08));

        // Get a set of the entries.
        Set<Map.Entry<String, Double>> setl = lhm.entrySet();

        // Display the set.
        for (Map.Entry<String, Double> me1 : setl) {
            System.out.print(me1.getKey() + ": ");
            System.out.println(me1.getValue());
        }

        System.out.println();

        // Deposit 1000 into John Doe's account.
        double balance = hm.get("John Doe");
        hm.put("John Doe", balance + 1000);

        System.out.println("John Doe's new balance: " +
                hm.get("John Doe"));

        hashMapPrint();
        linkHashMapprint();
    }

    private static void hashMapPrint() {
        System.out.println(" ");
        System.out.println(" ========================Hash Map========================== ");

        Map map = new HashMap<String, Integer>();

        for (int i = 0; i < 100; i++) {
            map.put("Test" + i, i);
        }

        Set<Map.Entry<String, Integer>> set = map.entrySet();

        for (Map.Entry<String, Integer> entry : set) {
            System.out.print(entry.getKey());
            System.out.println(" : " + entry.getValue());
        }
    }

    private static void linkHashMapprint() {
        System.out.println(" ");
        System.out.println(" ======================== Linked Hash Map========================== ");

        Map map = new LinkedHashMap<String, Integer>();

        for (int i = 0; i < 100; i++) {
            map.put("Test" + i, i);
        }

        Set<Map.Entry<String, Integer>> set = map.entrySet();

        for (Map.Entry<String, Integer> entry : set) {
            System.out.print(entry.getKey());
            System.out.println(" : " + entry.getValue());
        }
    }
}