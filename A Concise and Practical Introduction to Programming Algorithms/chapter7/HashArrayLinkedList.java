class HashArrayOpenAddressCh6 {

    static int m = 23;

    static int String2Integer(String s) {
        int result = 0;

        for (int j = 0; j < s.length(); j++)
            result += (int) s.charAt(j);

        return result;
    }

    // Note that m is a static variable
    static int HashFunction(int l) {
        return l % m;
    }

    public static void main(String[] args) {
        String[] animals = {"cat", "dog", "parrot", "horse", "fish", "shark", "pelican", "tortoise", "whale", "lion",
                "flamingo", "cow", "snake", "spider", "bee", "peacock", "elephant", "butterfly"};
        int i;
        String[] HashTable = new String[m];
        // By default HashTable[i]=null


        //ListString Toto=new ListString("Test",null);


        for (i = 0; i < animals.length; i++) {
            int s2int = String2Integer(animals[i]);
            int pos = HashFunction(s2int);

            while (HashTable[pos] != null)
                pos = (pos + 1) % m;

            HashTable[pos] = animals[i];
        }


        for (i = 0; i < m; i++)
            System.out.println("Position " + i + "\t" + HashTable[i]);

    }
}