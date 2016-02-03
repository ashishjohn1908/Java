package hsbc;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 26-Sep-2010
 * Time: 17:30:45
 * To change this template use File | Settings | File Templates.
 */
public interface ScreenEntry <V,T>{
    public <V,T> boolean logon(V user, T password);
}
