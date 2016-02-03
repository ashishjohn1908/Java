class BalanceSheet2 {
    public static void main(String[] args) {
        int credit;
        int debit;

        credit = 100 + 150;
        System.out.print("Total credit (in US dollars):\t");
        System.out.println(credit);

        debit = 50 + 25 + 100;
        System.out.print("Total debit (in US dollars):\t");
        System.out.println(debit);

        System.out.print("Balance:");
        System.out.println(credit - debit);
    }
}