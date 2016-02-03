import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 13/03/11
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public final class MyImmutable {
    private final int [] myArr;

    public MyImmutable(int[] inArr){
       myArr = inArr;  // Fails
       // myArr = Arrays.copyOf(inArr,3);
       // myArr = inArr.clone();
       // myArr = new int[inArr.length];
       // System.arraycopy(inArr,0,myArr,0,inArr.length);
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer(myArr.length);

        for (int aMyArr : myArr) {
            stringBuffer.append(aMyArr);
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        int[] myArrIn = {1,2,3};
        MyImmutable myImmutable = new MyImmutable(myArrIn);
        System.out.println(myImmutable.toString());
        myArrIn[1]=9;
        System.out.println(myImmutable.toString());
    }
}
