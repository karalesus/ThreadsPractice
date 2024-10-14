package BoundedBufferTask;

import java.util.Arrays;

public class BoundedBuffer<T> {
    private final T[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = -1;

    @SuppressWarnings("unchecked")
    public BoundedBuffer(int size) {
        buffer = (T[]) new Object[size];
    }


    public synchronized void put(T item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[in] = item;
        in++;
        out++;
        count++;
        notifyAll();
        System.out.println("Добавлен: " + item + '\n' + "Массив после добавления: " + Arrays.toString(buffer));
    }

    public synchronized T take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        T element = buffer[out];
        System.out.println("Извлечен: " + element);
        buffer[out] = null;
        System.out.println("Массив после извлечения: " + Arrays.toString(buffer));
        out--;
        count--;
        in--;
        notifyAll();
        return element;
    }

}
