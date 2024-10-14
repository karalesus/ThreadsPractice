package threads.sync.bankAccount;

import java.util.Random;

class BankAccount {
    private int balance = 1000;

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount)
            balance -= amount;
    }

}

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Random random = new Random();

        final int NUM_THREADS = 5;
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            int randomValue = random.nextInt(2);
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (randomValue == 1) account.deposit(100);
                    account.withdraw(100);
                }
            });
            threads[i].start();
        }
        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i].join();
            }
            System.out.println("Все потоки завершили работу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Финальный баланс: ");
    }
}
