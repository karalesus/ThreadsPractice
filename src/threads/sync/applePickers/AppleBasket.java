package threads.sync.applePickers;

public class AppleBasket {
    private int apples = 0;
    private final int pickedApples = 0;
    private final int maxApples = 20;

    public synchronized boolean pickApple(String picker) {
        if (apples < maxApples) {
            apples++;
            System.out.println(picker + " собрал. Всего яблок: " + apples);
            return true;
        }
        return false;
    }

    public int getApples() {
        return apples;
    }

    public int getMaxApples() {
        return maxApples;
    }
}
