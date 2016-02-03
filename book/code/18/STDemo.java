// Demonstrate StringTokenizer.

import java.util.StringTokenizer;

class STDemo {
    static String inStr = "title=Java: The Complete Reference;" +
            "author=Schildt;" +
            "publisher=Osborne/McGraw-Hill;" +
            "copyright=2006";

    public static void main(String args[]) {
        StringTokenizer st = new StringTokenizer(inStr, "=;");
        System.out.println(st.countTokens());
        while (st.hasMoreTokens()) {
            String key = st.nextToken();
            String val = st.nextToken();
            System.out.println(key + "\t" + val);

        }
    }


}