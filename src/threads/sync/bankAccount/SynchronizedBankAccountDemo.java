package threads.sync.bankAccount;

import java.util.Random;

public class SynchronizedBankAccountDemo {
    public static void main(String[] args) {
        SynchronizedBankAccount account = new SynchronizedBankAccount();
        Random random = new Random();

        final int NUM_THREADS = 5;
        Thread[] threads = new Thread[NUM_THREADS];
        int finalBalance = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            int randomValue = random.nextInt(0, 2);
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (randomValue == 1) {
                        account.deposit(100);
                        System.out.println("+100: " + account.getAndSetBalance(0, 0));
                    } else {
                        account.withdraw(100);
                        System.out.println("-100: " + account.getAndSetBalance(0, 0));
                    }
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

        System.out.println("Финальный баланс: " + account.getAndSetBalance(0, 0));
    }
}

