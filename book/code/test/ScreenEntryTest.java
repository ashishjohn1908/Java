import hsbc.ScreenEntry;
import hsbc.ScreenEntryImp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 22-Sep-2010
 * Time: 19:15:19
 * To change this template use File | Settings | File Templates.
 */
public class ScreenEntryTest {
    private ScreenEntry<String,String> entry;

    @Before
    public void setUp() {
        entry = new ScreenEntryImp<String, String>("plamen","password");
    }

    @Test
    public void logon() {
        boolean expected = true;
        boolean result = entry.logon("plamen", "password");
        assertEquals(expected, result);
    }

}
