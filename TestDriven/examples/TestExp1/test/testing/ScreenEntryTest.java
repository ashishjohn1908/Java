package testing;

import hsbc.ScreenEntry;
import hsbc.ScreenEntryImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 26-Sep-2010
 * Time: 17:32:04
 * To change this template use File | Settings | File Templates.
 */
public class ScreenEntryTest {
    
    private ScreenEntry<String, String> entry;

    @Before
    public void setUp(){
        entry = new ScreenEntryImpl<String, String>("plamen","password");
    }

    @Test
    public void logon(){

            boolean actual = entry.logon("plamen","password");
            assertTrue(actual);
    }
}
