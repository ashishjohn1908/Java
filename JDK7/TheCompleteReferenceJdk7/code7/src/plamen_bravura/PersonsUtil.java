package plamen_bravura;

import java.util.*;


/**
 * Created by plamen on 17/06/2014.
 */
public class PersonsUtil {

    private static class PersonsComparator implements Comparator<Persons>{



        @Override
        public int compare(Persons o1, Persons o2) {
            /**
             int r = o2.getSurName().compareTo(o1.getSurName());
             return (r != 0 ? r : o2.getFirName().compareTo(o1.getFirName()));
            */
            return o1.compareTo(o2);
        }
    }

    public static void sort(List<Persons> list, boolean withComp){
        if(withComp) {
            //PersonsComparator comparator = new PersonsComparator();
            Comparator<Persons> comparator = new Comparator<Persons>() {
                @Override
                public int compare(Persons o1, Persons o2) {
                    return o1.compareTo(o2);
                }
            };
            Collections.sort(list, comparator);
        } else {
            Collections.sort(list);
        }
    }

    public static void main(String[] args) {
        List<Persons> list = new ArrayList<>();
        Persons person1 = new Persons("Plamen", "Stilyianov", new GregorianCalendar(1968,6,29).getTime());
        Persons person2 = new Persons("Panayotis", "Sarandos", new GregorianCalendar(1970,4,20).getTime());
        Persons person3 = new Persons("Serge", "Yakovlev", new GregorianCalendar(1946,3,15).getTime());
        Persons person4 = new Persons("Enica", "Ivanova", new GregorianCalendar(1987,10,9).getTime());
        Persons person5 = new Persons("Svetlozar", "Stilyianov", new GregorianCalendar(1968,6,29).getTime());
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);

        System.out.println("Before Sorting...");
        for(Persons p : list)
            System.out.println(p.toString());

        sort(list,true);
        System.out.println("After Sorting...");
        System.out.printf("+++++++++++++++++++++++++++++++++++++++++\n");
        for(Persons p : list)
            System.out.println(p.toString());
    }
}
