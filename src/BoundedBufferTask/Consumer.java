package BoundedBufferTask;

import java.util.concurrent.CountDownLatch;

public class Consumer implements Runnable {
    private final BoundedBuffer<Integer> boundedBuffer;
    private final CountDownLatch startSignal;
    private final CountDownLatch finishSignal;

    public Consumer(BoundedBuffer<Integer> boundedBuffer, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.boundedBuffer = boundedBuffer;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            boundedBuffer.take();
            finishSignal.countDown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
