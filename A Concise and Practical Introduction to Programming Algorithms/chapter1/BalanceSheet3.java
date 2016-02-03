class BalanceSheet3 {
    public static void main(String[] args) {
        int credit1 = 100, credit2 = 150;
        int credit = credit1 + credit2;
        int debit1 = 50, debit2 = 25, debit3 = 100;
        int debit = debit1 + debit2 + debit3;
        int balance = credit - debit;

        System.out.print("Balance:");
        System.out.println(balance);
    }
}