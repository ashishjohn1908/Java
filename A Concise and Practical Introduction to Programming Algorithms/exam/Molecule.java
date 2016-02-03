public class Molecule {
    Atom[] atoms;
    Atom sphere;

    public Molecule(Atom[] t) {
        this.atoms = t;
        Point3D center = Atom.middle(atoms);
        double r = 0;
        for (int i = 0; i < atoms.length; ++i) {
            double ri = Atom.maxDistance(center, atoms[i]);
            if (r < ri) r = ri;
        }
        this.sphere = new Atom(center.x, center.y, center.z, r);
    }

    public static boolean bump(Atom a, Molecule b) {
        if (!Atom.bump(a, b.sphere))
            return false;
        for (int i = 0; i < b.atoms.length; ++i)
            if (Atom.bump(a, b.atoms[i]))
                return true;
        return false;
    }

    public static boolean bump(Molecule a, Molecule b) {
        if (!Atom.bump(a.sphere, b.sphere))
            return false;
        for (int i = 0; i < a.atoms.length; ++i)
            if (bump(a.atoms[i], b))
                return true;
        return false;
    }
}
