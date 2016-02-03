class DictionaryEntry {
    String word;
    String definition;

    DictionaryEntry(String w, String def) {
        this.word = new String(w); // Clone the strings
        this.definition = new String(def);
    }
}

class TestMyDictionary {
    public static void main(String[] args) {
        DictionaryEntry[] Dico = new DictionaryEntry[10];
        Dico[0] = new DictionaryEntry("Java", "A modern object-oriented programming language");
        Dico[1] = new DictionaryEntry("C++", "An effective object-oriented programming language");
        Dico[2] = new DictionaryEntry("FORTRAN", "FORTRAN stands for FORmula TRANSlation. Often used for simulation.");
//...
    }
}
