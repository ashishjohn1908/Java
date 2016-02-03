class ParseArgumentsMin {
    public static void main(String[] args) {
        int indexMin = 0;
        for (int i = 1; i < args.length; i++)
            if (Integer.parseInt(args[indexMin]) > Integer.parseInt(args[i]))
                indexMin = i;

        System.out.println("Maximum argument found at index:" + indexMin + " :" + args[indexMin]);
    }

}