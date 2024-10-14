package threads.sync.applePickers;

import java.util.concurrent.CountDownLatch;

public class Picker implements Runnable {
    private final AppleBasket basket;
    private final String name;
    private int pickedApples;
    private final CountDownLatch startSignal;
    private final CountDownLatch finishSignal;

    public Picker(AppleBasket basket, String name, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.basket = basket;
        this.name = name;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            System.out.println(name + " начал сбор");
            while (basket.pickApple(name)) {
                Thread.sleep(100);
                pickedApples++;
            }
            finishSignal.countDown();
            System.out.println(name + " собрал " + pickedApples);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

