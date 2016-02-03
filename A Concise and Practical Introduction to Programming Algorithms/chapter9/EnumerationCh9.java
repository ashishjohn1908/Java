class EnumerationCh9 {

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
        if (pos == selection.length - 1) {
            display(selection);
        } // terminal case, reach length n
        else {
            pos++;
            // Set the (pos+1)th bit to 1
            selection[pos] = true;
            Enumerate(selection, pos);
            // Set the (pos+1)th bit to 0
            selection[pos] = false;
            Enumerate(selection, pos);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int i;
// Binary representation of numbers
        boolean[] select = new boolean[n];
        for (i = 0; i < n; i++) {
            select[i] = false;
        } // optional since array creation set it to all zero
// Launching the enumeration from the first bit (index 0)	
        Enumerate(select, -1);
    }

}