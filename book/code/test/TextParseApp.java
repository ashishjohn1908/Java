/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 26-Jul-2010
 * Time: 21:32:25
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;
import java.io.*;

class TextParseApp {

    private static BufferedReader standard = null;


    public static void readLine() {
        try {
            ArrayList<String> strings = new ArrayList<String>();
            standard = new BufferedReader(new InputStreamReader(System.in));

            String inline = standard.readLine();

            StringTokenizer st = new StringTokenizer(inline.toLowerCase());
            while (st.hasMoreTokens()) {
                strings.add(st.nextToken());
            }

            HashMap<String, Integer> map = new HashMap<String, Integer>();
            int count = 0;
            for (int i = 0; i < strings.size(); i++) {
                count = 0;
                for (int j = 0; j < strings.size(); j++) {
                    if (strings.get(i).equals(strings.get(j)))
                        count++;
                }

                if (!map.containsKey(strings.get(i))) {
                    map.put(strings.get(i), count);
                } else {
                    map.remove(strings.get(i));
                    map.put(strings.get(i), count);
                }
            }

            System.out.println(map.toString());
            for (String s : map.keySet()) {
                System.out.println(s + " : " + map.get(s));
            }


        } catch (Exception e) {
            System.out.println("");
        }
    }

    public static void main(String args[]) throws IOException {
        readLine();
    }
}

