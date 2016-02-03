class Enumeration {

    static void display(boolean[] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i])
                System.out.print("1 ");
            else
                System.out.print("0 ");
        }

        System.out.println("");
    }

    static void Enumerate(boolean[] selection, int pos) {
        if (pos == selection.length - 1)
            return; //{display(selection);}
        else {
            pos++;

            selection[pos] = true;
            Enumerate(selection, pos);

            selection[pos] = false;
            Enumerate(selection, pos);
        }

    }

    public static void main(String[] args) {
        int n = 64;
        int i;
        boolean[] select = new boolean[n];
        for (i = 0; i < n; i++) {
            select[i] = false;
        }

        Enumerate(select, -1);
    }

}