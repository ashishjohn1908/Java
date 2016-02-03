class PointCh5 {
    int x, y;

    PointCh5(int xx, int yy) {
        x = xx;
        y = yy;
    }
} // end of class Point

class Skeleton {
    // Static class variables
    static int nbpoint = 0;
    static double x;
    static boolean[] prime;

    static int f1(int p) {
        return p / 2;
    }

    static int f2(int p) {
        return 2 * p;
    }

    public static void Display(PointCh5 p) {
        System.out.println("(" + p.x + "," + p.y + ")");
    }

    public static void main(String[] argArray) {
        System.out.println(f2(f1(3)) + " versus (!=) " + f1(f2(3)));
        PointCh5 p, q;
        p = new PointCh5(2, 1);
        nbpoint++;
        q = new PointCh5(3, 4);
        nbpoint++;
        Display(p);
        Display(q);
    }
}