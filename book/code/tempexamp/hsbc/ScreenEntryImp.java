package hsbc;

import com.sun.xml.internal.ws.message.saaj.SAAJHeader;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 22-Sep-2010
 * Time: 19:20:19
 * To change this template use File | Settings | File Templates.
 */
public class ScreenEntryImp<T, V> implements ScreenEntry<T, V>{

    private T user;
    private V password;

    public ScreenEntryImp(){}

    /**
     * Constructor used for testing
     * @param user
     * @param password
     */
    public ScreenEntryImp(T user, V password){
        this.user = user;
        this.password = password;
    }

    @Override
    public <T, V> boolean logon(T user, V password){

         return (this.user.equals(user) && this.password.equals(password));
    }

}
