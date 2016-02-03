class ArrayBound {
    public static void main(String[] args) {
        int[] v = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        long l = v.length;
        System.out.println("Size of array v:" + l);
        System.out.println(v[4]);
        System.out.println(v[12]);
    }
}