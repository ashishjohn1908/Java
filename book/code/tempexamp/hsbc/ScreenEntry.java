package hsbc;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 22-Sep-2010
 * Time: 19:14:12
 * To change this template use File | Settings | File Templates.
 */
public interface ScreenEntry<T, V> {
    <T, V> boolean logon(T user, V password);
}
