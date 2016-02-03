import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 14-Jul-2009
 * Time: 14:49:48
 * To change this template use File | Settings | File Templates.
 */
public class CollectionExmp {

    public static void main(String[] args) {

        Collection<String> collection = new HashSet<String>();
        collection.add("one");
        collection.add("two");

        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
        System.out.println("======================================");

        TreeSet<String> set = new TreeSet<String>(collection);
        Iterator<String> itor = set.iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }


        Collection<String> colList = new ArrayList<String>();
        colList.add("one");
        colList.add("two");
        colList.add("three");

        if (colList.containsAll(collection)) {
            System.out.println("true");
        }

        System.out.println("=================");

        String[] strings = colList.toArray(new String[0]);
        String[] strings1 = (String[]) colList.toArray();

    }
}
