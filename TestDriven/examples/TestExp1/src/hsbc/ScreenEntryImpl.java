package hsbc;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 26-Sep-2010
 * Time: 17:37:23
 * To change this template use File | Settings | File Templates.
 */
public class ScreenEntryImpl<V,T> implements ScreenEntry<V,T> {

    private V user;
    private T password;
    
    public ScreenEntryImpl(V user, T password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public <V,T> boolean logon(V user, T password) {

        return validate(user, password);
    }


    private <V,T> boolean validate(V user, T password) {
        if(this.user.equals(user) && this.password.equals(password))
            return true;
        return false;
    }
}
