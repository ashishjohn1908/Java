package BaseStats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:20:40
 * To change this template use File | Settings | File Templates.
 */

public class inputmod {
    public static String readString() {
        BufferedReader buffin = new BufferedReader(new InputStreamReader(System.in), 1);
        String string = " ";
        try {
            string = buffin.readLine();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        return string;

    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static double readDouble() {
        return Double.parseDouble(readString());
    }

    public static byte readByte() {
        return Byte.parseByte(readString());
    }

    public static long readLong() {
        return Long.parseLong(readString());
    }

    public static float readFloat() {
        return Float.parseFloat(readString());
    }

    public static short readShort() {
        return Short.parseShort(readString());
    }

}
