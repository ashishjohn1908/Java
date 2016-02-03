class StringMethods {
    public static void main(String[] args) {
//String s= "Supercalifragilisticexpialidocious";
//System.out.println(s.length());

        String s1 = "java";
        String s2 = "JAVA";
        System.out.println(s1.equals(s2));

        String s = "3.14159265";
        System.out.println(s.charAt(1));
        char c = s.charAt(1);

        String u = "Polyhedron", v = "Polyhedral";
        System.out.println(u.compareTo(v));
        System.out.println("o:" + (int) 'o');
        System.out.println("a:" + (int) 'a');
        int diff = 'o' - 'a'; // implicit casting char->int
        System.out.println(diff);

        String a = "polyhedral", b = "polyhedralization";
        System.out.println(a.compareTo(b));
        System.out.println(a.length() - b.length());

    }
}
