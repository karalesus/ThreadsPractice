package threads.notSync;

public class BasicThreadDemo {
    public static void main(String[] args) throws InterruptedException {
// Создаем и запускаем первый поток, традиционно, через анонимный объект
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Поток 1: " + i);
                    try {
                        // Приостанавливаем поток на 1 секунду
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
// Создаем и запускаем второй поток, но уже с использованием лямбда- выражения
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Поток 2: " + i);
                try {
                    // Приостанавливаем поток на 1.5 секунды
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 1 - многоядерный процессор
        // 2 - прервание потока еще на этапе сравнения его с 5
        // 3 - хаотичность распределения потоков. Мы не знаем, сколько времени выделено на тот или иной поток.

// Запускаем потоки, они начнут работу параллельно основному потоку
// Учтите, что порядок выполнения потоков не гарантирован!
// Запустите программу несколько раз (5-6) и убедитесь в этом...
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
// Ожидаем завершения потоков
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Все потоки завершили работу!");
    }
}