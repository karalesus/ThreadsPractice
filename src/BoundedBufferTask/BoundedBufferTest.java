package BoundedBufferTask;

import java.util.concurrent.CountDownLatch;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<Integer>(10);
        int operationCount = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(operationCount * 2); // финиш сигнал = количество потоков!
        for (int i = 1; i <= operationCount; i++) {
            new Thread(new Producer(boundedBuffer, startSignal, finishSignal)).start();
            new Thread(new Consumer(boundedBuffer, startSignal, finishSignal)).start();
        }
        System.out.println("Производители и потребители начали работать!");

        startSignal.countDown();
        finishSignal.await();

        System.out.println("Производители и потребители закончили!");
    }
}
