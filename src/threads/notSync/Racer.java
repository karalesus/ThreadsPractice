package threads.notSync;

class Racer implements Runnable {
    private final String name;
    private int count = 0;

    public Racer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
        long endTime = System.nanoTime();
        System.out.println(name + " финишировал. Время выполнения: " + (endTime - startTime));
    }
}

class RacerDemo {
    public static void main(String[] args) {

        final int NUM_RACERS = 10;
        Thread[] threads = new Thread[NUM_RACERS];
        for (int i = 1; i <= NUM_RACERS; i++) {
            threads[i - 1] = new Thread(new Racer("Гонщик " + i));
            threads[i - 1].start();
        }
        try {
            for (int i = 1; i < NUM_RACERS; i++) {
                threads[i].join();
            }
            System.out.println("Все гонщики прибыли");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}