public class Point3D {
    double x, y, z;

    public Point3D(double x0, double y0, double z0) {
        this.x = x0;
        this.y = y0;
        this.z = z0;
    }

    static double sqr(double x) {
        return x * x;
    }

    public static double distance(Point3D p, Point3D q) {
        return Math.sqrt(sqr(q.x - p.x) + sqr(q.y - p.y)
                + sqr(q.z - p.z));
    }

    public static Point3D add(Point3D p, Point3D q) {
        return new Point3D(p.x + q.x, p.y + q.y, p.z + q.z);
    }

    public static void scale(Point3D p, double k) {
        p.x *= k;
        p.y *= k;
        p.z *= k;
    }
}
