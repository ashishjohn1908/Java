/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 27/02/11
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public class ContinueWithLabelDemo {

    public static void main(String[] args) {

        String searchMe = "Look for a substring in me";
        String substring = "sub";
        boolean foundIt = false;

        int max = searchMe.length() - substring.length();

        test:
        for (int i = 0; i <= max; i++) {
            int n = substring.length();
            int j = i;
            int k = 0;
            while (n-- != 0) {
                if (searchMe.charAt(j++) != substring.charAt(k++)) {
                    continue test;
                }
            }
            foundIt = true;
            break;
        }
        System.out.println(foundIt ? "Found it" : "Didn't find it");
    }
}
