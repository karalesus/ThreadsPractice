package threads.sync.racer;

import java.util.concurrent.CountDownLatch;

class SynchronizedRacer implements Runnable {
    private final String name;
    private final CountDownLatch startSignal;
    private final CountDownLatch finishSignal;
    private int count = 0;

    public SynchronizedRacer(String name, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.name = name;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await(); // Ждем сигнала старта
            System.out.println(name + " начал гонку");

            // реализуйте гонку
            long startTime = System.nanoTime();
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
            long endTime = System.nanoTime();
            System.out.println("Время выполнения " + name + " " + (endTime - startTime));
            finishSignal.countDown(); // Сигнализируем о завершении
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
