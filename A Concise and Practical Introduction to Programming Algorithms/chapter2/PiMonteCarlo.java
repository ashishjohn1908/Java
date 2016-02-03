class MonteCarloPI {
    public static void main(String[] args) {
        int iter = 10000000; // # iterations
        int hits = 0;
        for (int i = 0; i < iter; i++) {
            double rX = 2 * Math.random() - 1.0;
            double rY = 2 * Math.random() - 1.0;
            double dist = rX * rX + rY * rY;
            if (dist <= 1.0) // falls inside the disk
                hits++;
        }
        double ratio = (double) hits / iter; // Ratio of areas
        double area = ratio * 4.0;
        System.out.println("Estimation of PI: " + area + " versus library PI " + Math.PI);
    }
}