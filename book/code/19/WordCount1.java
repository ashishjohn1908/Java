// Enhanced word count program that uses a StreamTokenizer

import java.io.*;

class WordCount1 {
    public static int words = 0;
    public static int lines = 0;
    public static int chars = 0;

    public static void wc(Reader r) throws IOException {
        StreamTokenizer tok = new StreamTokenizer(r);

        tok.resetSyntax();
        tok.wordChars(33, 255);
        tok.whitespaceChars(0, ' ');
        tok.eolIsSignificant(true);

        while (tok.nextToken() != StreamTokenizer.TT_EOF) {
            switch (tok.ttype) {
                case StreamTokenizer.TT_EOL:
                    lines++;
                    chars++;
                    break;
                case StreamTokenizer.TT_WORD:
                    words++;
                default: // FALLSTHROUGH
                    chars += tok.sval.length();
                    break;
            }
        }
    }

    public static void main(String args[]) throws IOException {
        if (args.length == 0) { // We're working with stdin
            wc(new InputStreamReader(System.in));
            System.out.println(lines + " " + words + " " + chars);
        } else { // We're working with a list of files
            int twords = 0, tchars = 0, tlines = 0;
            for (String arg : args) {
                try {
                    words = chars = lines = 0;
                    wc(new FileReader(arg));
                    twords += words;
                    tchars += chars;
                    tlines += lines;
                    System.out.println(arg + ": " + lines + " " + words + " " + chars);
                } catch (IOException e) {
                    System.out.println(arg + ": error.");
                }
            }
            System.out.println("total: " + tlines + " " + twords + " " + tchars);
        }
    }
}