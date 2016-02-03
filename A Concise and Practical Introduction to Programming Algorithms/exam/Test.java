public class Test {
    public static void main(String[] args) {
        Atom o = new Atom(0, 0.4, 0, Atom.O_RADIUS);
        Atom h1 = new Atom(0.76, -0.19, 0, Atom.H_RADIUS);
        Atom h2 = new Atom(-0.76, -0.19, 0, Atom.H_RADIUS);
        Atom[] H2O = {o, h1, h2};
        Molecule mol = new Molecule(H2O);
        // construction de la figure
        double[] covalence = {0.7, 0.4, 0.4};
        for (int i = 0; i < mol.atoms.length; ++i) {
            System.out.println("\\pgfcircle[stroke]{\\pgfpoint{"
                    + mol.atoms[i].center.x + "cm}{" + mol.atoms[i].center.y + "cm}}{"
                    + mol.atoms[i].radius + "cm}");
            System.out.println("\\pgfcircle[fill]{\\pgfpoint{"
                    + mol.atoms[i].center.x + "cm}{" + mol.atoms[i].center.y
                    + "cm}}{1pt}");
        }
        System.out.println("\\pgfcircle[stroke]{\\pgfpoint{"
                + (int) (100 * mol.sphere.center.x) / 100.0 + "cm}{"
                + (int) (100 * mol.sphere.center.y) / 100.0 + "cm}}{1pt}");
        System.out.println("\\pgfsetdash{{3pt}{3pt}}{0pt}");
        System.out.println("\\pgfcircle[stroke]{\\pgfpoint{"
                + (int) (100 * mol.sphere.center.x) / 100.0 + "cm}{"
                + (int) (100 * mol.sphere.center.y) / 100.0 + "cm}}{"
                + (int) (100 * mol.sphere.radius) / 100.0 + "cm}");
        System.out.println("\\pgfsetdash{{1pt}{3pt}}{0pt}");
        for (int i = 0; i < mol.atoms.length; ++i) {
            System.out.println("\\pgfcircle[stroke]{\\pgfpoint{"
                    + mol.atoms[i].center.x + "cm}{" + mol.atoms[i].center.y + "cm}}{"
                    + covalence[i] + "cm}");
        }
    }
}