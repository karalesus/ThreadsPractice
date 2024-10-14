package threads.notSync;

public class MultiThreadDemo {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadNumber = i + 1;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("№ потока: " + threadNumber + " По счету: " + i);
                    }
                }
            });
            threads[i].start();
        }
        try {
            for (int i = 0; i < 5; i++) {
                threads[i].join();
            }
            System.out.println("Все потоки завершили работу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}