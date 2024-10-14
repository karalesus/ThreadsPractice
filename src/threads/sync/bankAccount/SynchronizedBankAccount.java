package threads.sync.bankAccount;

public class SynchronizedBankAccount {
    private volatile int balance = 10000000;

    public void deposit(int amount) {
        getAndSetBalance(1, amount);
    }

    public void withdraw(int amount) {
        getAndSetBalance(-1, amount);
    }

    public synchronized int getAndSetBalance(int operation, int amount) {
        if (balance >= amount && operation == -1) balance -= amount;
        if (operation == 1) balance += amount;
        if (operation == 0 && amount == 0) return balance;
        return balance;
    }


}

