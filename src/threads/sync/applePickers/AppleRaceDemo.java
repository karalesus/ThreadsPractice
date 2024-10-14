package threads.sync.applePickers;

import java.util.concurrent.CountDownLatch;

public class AppleRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        AppleBasket basket = new AppleBasket();
        int pickerCount = 2;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(pickerCount);
        for (int i = 1; i <= pickerCount; i++) {
            new Thread(new Picker(basket, "Сборщик " + i, startSignal, finishSignal)).start();
        }
        System.out.println("Гонка начинается!");
        startSignal.countDown();
        finishSignal.await();

        System.out.println("Гонка завершена! Всего собрано яблок: " + basket.getApples());
    }
}
