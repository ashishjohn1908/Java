package net.jcip.examples;

import java.math.BigDecimal;
import java.util.concurrent.atomic.*;

/**
 * DynamicOrderDeadlock
 * <p/>
 * Dynamic lock-ordering deadlock
 *
 * @author Brian Goetz and Tim Peierls
 */
public class DynamicOrderDeadlock {
    // Warning: deadlock-prone!
    public static void transferMoney(Account fromAccount,
                                     Account toAccount,
                                     DollarAmount amount)
            throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();
                else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

    static class DollarAmount implements Comparable<DollarAmount> {
        // Needs implementation
        private BigDecimal balance = new BigDecimal(0);

        public DollarAmount(int amount) {
            this.balance = new BigDecimal(amount);
        }

        public DollarAmount add(DollarAmount d) {
            this.balance = this.getBalance().add(d.getBalance());
            return this;
        }

        public DollarAmount subtract(DollarAmount d) {
            this.balance = this.getBalance().subtract(d.getBalance());
            return this;
        }

        public int compareTo(DollarAmount dollarAmount) {
            return this.getBalance().compareTo(dollarAmount.getBalance());
        }

        public BigDecimal getBalance(){
            return this.balance;
        }
    }

    static class Account {
        private DollarAmount balance;
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            acctNo = sequence.incrementAndGet();
        }

        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    static class InsufficientFundsException extends Exception {
    }
}
