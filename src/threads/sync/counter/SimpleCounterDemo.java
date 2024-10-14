package threads.sync.counter;

public class SimpleCounterDemo {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        final int INCREMENTS_PER_THREAD = 100000;
        final int MAX_COUNT = INCREMENTS_PER_THREAD * NUM_THREADS;
        SimpleCounter counter = new SimpleCounter(MAX_COUNT); // общий ресурс
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                        counter.increment();
                    }
                    for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                        counter.decrement();
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
        System.out.println("Максимальное значение: " + MAX_COUNT);
        System.out.println("Фактическое значение: " + counter.getCount());
    }
}