import com.sun.org.apache.bcel.internal.generic.NEW;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 11/06/11
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public class RadSimpleDateFormatDemo {

    public static void main(String[] args) throws ParseException {
        String value = "11/06/2011";
        Locale  locale = Locale.US;
        //locale.getCountry();
        SimpleDateFormat localeDf = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM,locale);
        localeDf.applyPattern("dd/MM/yyyy");

        Date formattedValue = localeDf.parse(value);
        SimpleDateFormat simpleDateFormat1 =    new SimpleDateFormat("dd-MMM-yy",Locale.US);
        String date1 = simpleDateFormat1.format(formattedValue);
        System.out.println(date1.toUpperCase());
    }

}
