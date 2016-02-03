class RaggedArray {
    public static void main(String[] args) {
        int ragged[][] = new int[5][];
        for (int i = 0; i < 5; i++) {
            ragged[i] = new int[i + 1];
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < ragged[i].length; j++) {
                ragged[i][j] = (int) (10 * Math.random());
            }
        }

        System.out.println("type:" + ragged + " " + ragged.length);
        for (int i = 0; i < 5; i++){
            System.out.println("type:" + ragged[i] + " " + ragged[i].length);
        }

    }
}