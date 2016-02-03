package hsbc;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 22-Sep-2010
 * Time: 22:31:08
 * To change this template use File | Settings | File Templates.
 */
public class SubClazz extends SuperClazz {

    private SuperClazz.StatInerClazz a = new StatInerClazz();
    private InstInerClazz b = new InstInerClazz();
    private String c = super.b;
    private boolean d = super.helloWorld();

    class MyLfe extends InstInerClazz {

    }

    private SuperClazz.StatInerClazz clazz1;

    public SubClazz() {
        super();
    }


    public static void main(String[] args) {
    }
}
