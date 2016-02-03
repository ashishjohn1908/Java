class OverflowBug {

    public static void main(String[] args) {
        int n = 2147483647; //2^31-1;
        int nval = n + 1;
        System.out.println("If n=" + n + " then n+1=" + nval);

        long m = 9223372036854775807L;
        long mval = m + 1;
        System.out.println("If m=" + m + " then m+1=" + mval);
    }
}