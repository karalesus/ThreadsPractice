package threads.notSync;

public class CounterDemo {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        final int INCREMENTS_PER_THREAD = 100000;
        Counter counter = new Counter(); // общий ресурс
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                        counter.increment();
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
        System.out.println("Ожидаемое значение: " + (NUM_THREADS * INCREMENTS_PER_THREAD));
        System.out.println("Фактическое значение: " + counter.getCount());
    }
}