package ch29;

// Use collect() to create a List and a Set from a stream.

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class NamePhoneEmail1 {
    String name;
    String phonenum;
    String email;

    NamePhoneEmail1(String n, String p, String e) {
        name = n;
        phonenum = p;
        email = e;
    }
}

class NamePhone1 {
    String name;
    String phonenum;

    NamePhone1(String n, String p) {
        name = n;
        phonenum = p;
    }
}

class StreamDemo7 {

    public static void main(String[] args) {

        // A list of names, phone numbers, and e-mail addresses.
        ArrayList<NamePhoneEmail1> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail1("Larry", "555-5555",
                "Larry@HerbSchildt.com"));
        myList.add(new NamePhoneEmail1("James", "555-4444",
                "James@HerbSchildt.com"));
        myList.add(new NamePhoneEmail1("Mary", "555-3333",
                "Mary@HerbSchildt.com"));

        // Map just the names and phone numbers to a new stream.
        Stream<NamePhone1> nameAndPhone = myList.stream().map(
                (a) -> new NamePhone1(a.name, a.phonenum)
        );

        // Use collect to create a List of the names and phone numbers.
        List<NamePhone1> npList = nameAndPhone.collect(Collectors.toList());

        System.out.println("Names and phone numbers in a List:");
        for (NamePhone1 e : npList)
            System.out.println(e.name + ": " + e.phonenum);

        // Obtain another mapping of the names and phone numbers.
        nameAndPhone = myList.stream().map(
                (a) -> new NamePhone1(a.name, a.phonenum)
        );

        // Now, create a Set by use of collect().
        Set<NamePhone1> npSet = nameAndPhone.collect(Collectors.toSet());

        System.out.println("\nNames and phone numbers in a Set:");
        for (NamePhone1 e : npSet)
            System.out.println(e.name + ": " + e.phonenum);
    }
}

