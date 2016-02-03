import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;

class Account {
    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }

    private int balance = 50;

}

public class AccountDanger implements Runnable {
    private static Thread t;
    private static Account account = new Account();

    public static void main(String[] args) {
        AccountDanger danger = new AccountDanger();
        t = new Thread(danger);
        t.setName("Fred");
        t.start();

        t = new Thread(danger);
        t.setName("Lucy");
        t.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            makeWithdrawal(10);
            if (account.getBalance() < 0) {
                System.out.println("account is overdrawn!");
            }

        }
    }

    private void makeWithdrawal(int amt) {
        synchronized (account) {
            if (account.getBalance() >= amt) {
                System.out.println(Thread.currentThread().getName() + " is going to withdraw");

                try {
                    Thread.sleep(500);
                    account.withdraw(amt);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " completes the withdrawal");
                }
            } else {
                System.out.print("Not enough in account for " + Thread.currentThread().getName() + " to withdraw ");
                System.out.println(account.getBalance());
            }
        }
    }
}
