package threads.sync.counter;

public class SimpleCounter {
    private int count = 0;
    private final int maxCount;

    public SimpleCounter(int maxCount) {
        this.maxCount = maxCount;
    }

    //помечен как synchronized, что гарантирует, что только один поток может увеличивать count в любой момент времени.
    public synchronized void increment() {
        if (count < maxCount) {
            count++;
        } else {
        }
    }

    public synchronized void decrement() {
        if (count > 0) {
            count--;
        } else {
        }
    }

    public synchronized void decrement(int limit) {
        if (count == limit) return;
        count--;
    }

    //также помечен как synchronized, чтобы обеспечить корректное считывание значения count
    public synchronized int getCount() {
        return count;
    }
}