package hsbc;

import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 03-Oct-2010
 * Time: 18:57:40
 * To change this template use File | Settings | File Templates.
 */
public class GetProps {
    public static void main(String[] args) {

        /* Test reading properties w & w/out security manager */

        String s;

        try {

            System.out.println("About to get os.name property value");

            s = System.getProperty("os.name", "not specified");
            System.out.println("  The name of your operating system is: " + s);

            System.out.println("About to get java.version property value");

            s = System.getProperty("java.version", "not specified");
            System.out.println("  The version of the JVM you are running is: " + s);

            System.out.println("About to get user.home property value");

            s = System.getProperty("user.home", "not specified");
            System.out.println("  Your user home directory is: " + s);

            System.out.println("About to get java.home property value");

            s = System.getProperty("java.home", "not specified");
            System.out.println("  Your JRE installation directory is: " + s);
            System.out.println(" ");
            System.out.println("===========================================");
            System.out.println(" ");
            Properties p = System.getProperties();
            for (Map.Entry entry : p.entrySet()) {
                String s1 = (String) entry.getKey();
                String s2 = (String) entry.getValue();
                System.out.println(s1 + " : " + s2);
            }

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }

    }

}
