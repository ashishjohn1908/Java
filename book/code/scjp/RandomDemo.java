import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: plamen
 * Date: 01/08/12
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class RandomDemo {
    private static int[] randomNums;

    // Probability of the occurence of randomNums private float[] probabilities;
    private static float[] probabilities;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 100; i++){
            list.add(nextNum());
        }
        int x0 =0, x1 =0 , x2 =0, x3 =0, x4 =0;
        for(Integer integer : list){
            if(integer == -2){
                x0++;
            }else if (integer == -1){
                x1++;
            } else if (integer == 0){
                x2++;
            }else if (integer == 1){
                x3++;
            }else if (integer == 2){
                x4++;
            }
        }
        System.out.println("-2 : " + x0 + " times");
        System.out.println("-1 : " + x1 + " times");
        System.out.println("0 : " + x2 + " times");
        System.out.println("1 : " + x3 + " times");
        System.out.println("2 : " + x4 + " times");
        System.out.println(0.01f + " : " + (float)x0/100f);
    }

    public static  int nextNum() {
        randomNums = new int[]{-2, -1, 0, 1, 2};
        probabilities = new float[]{0.01f, 0.2f, 0.58f, 0.2f, 0.01f};

        Random random = new Random();
        float prob = random.nextFloat();

        if (prob <= probabilities[0]){
                return randomNums[0];
        } else if (prob <= (probabilities[1] + probabilities[0])) {
                return randomNums[1];
        } else if (prob <= (probabilities[2] + probabilities[1] + probabilities[0])) {
                return randomNums[2];
        } else if (prob <= probabilities[3] + probabilities[2] + probabilities[1] + probabilities[0]) {
                return randomNums[3];
        }
        return randomNums[4];
    }
}
