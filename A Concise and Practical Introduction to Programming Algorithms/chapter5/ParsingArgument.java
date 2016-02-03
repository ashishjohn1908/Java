class ParsingArgument {
    public static void main(String[] args) {
        String minimum = args[0];

        for (int i = 1; i < args.length; i++)
            if (minimum.compareTo(args[i]) > 0)
                minimum = args[i];

        System.out.println("Lexicographically minimum string is:" + minimum);
    }
}