public class Atom {
    Point3D center;
    double radius;

    public static final double H_RADIUS = 1.2;
    public static final double O_RADIUS = 1.5;

    public Atom(double x, double y, double z, double rad) {
        this.center = new Point3D(x, y, z);
        this.radius = rad;
    }

    public static boolean bump(Atom a, Atom b) {
        return Point3D.distance(a.center, b.center)
                < a.radius + b.radius;
    }

    //suite de la classe Atom

    public static Point3D middle(Atom[] t) {
        Point3D middle = t[0].center;
        for (int i = 1; i < t.length; ++i)
            middle = Point3D.add(middle, t[i].center);
        Point3D.scale(middle, 1.0 / t.length);
        return middle;
    }

    public static double maxDistance(Point3D p, Atom a) {
        return Point3D.distance(p, a.center) + a.radius;
    }

    public Atom(Point3D c, double rad) {
        this.center = c;
        this.radius = rad;
    }

}
