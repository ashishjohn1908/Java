class ArrayMinimum {

    static int minArray(int[] t) {
        int m = t[0];
        for (int i = 1; i < t.length; ++i)
            if (t[i] < m)
                m = t[i];
        return m;
    }

    public static void main(String[] args) {
        int[] v = new int[23];

        for (int i = 0; i < 23; i++)
            v[i] = (int) (Math.random() * 100); // int from 0 to 99

        System.out.println("The minimum of the array is :" + minArray(v));
    }
}