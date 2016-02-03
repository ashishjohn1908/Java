package javas;

import java.util.Map;
import java.util.HashMap;
import static java.util.Map.Entry;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 03-Feb-2008
 * Time: 00:38:40
 * To change this template use File | Settings | File Templates.
 */
public class Template {

    private Map<String, String> varaiables;
    private String templateText;

    public Template(String templateString) {
        this.varaiables = new HashMap<String, String>();
        this.templateText = templateString;
    }

    public void set(String name, String value) {
        this.varaiables.put(name,value);
    }

    public Object evaluate() {
        String result = templateText;

        for(Entry<String, String> entry : varaiables.entrySet()){
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex,entry.getValue());
        }

        return result;
    }
}
