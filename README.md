# Асинхронное и параллельное программирование: задания с практических занятий

В директории BoundedBufferTask реализован класс BoundedBuffer<T>, который представляет собой буфер ограниченного размера. Этот буфер должен поддерживать операции добавления (put) и извлечения (take) элементов. Буфер должен быть потокобезопасным, что означает, что несколько потоков могут одновременно вызывать методы put и take без нарушения целостности данных.

**1.	Реализован метод put:**
   
a.	Если буфер полон (count == buffer.length), поток должен ждать, используя wait().

b.	После освобождения места добавить элемент в буфер.

c.	Увеличить count и обновить in.

d.	Вызвать notifyAll() для оповещения ожидающих потоков.

**2. Реализован метод take:**

a.	Если буфер пуст (count == 0), поток должен ждать, используя wait().

b.	После появления элемента извлечь его из буфера.

c.	Уменьшить count и обновить out.

d.	Вызвать notifyAll() для оповещения ожидающих потоков.

e.	Вернуть извлеченный элемент.

**Создан класс BoundedBufferTest для проверки работы.**
1.	Созданы несколько потоков-производителей, которые добавляют элементы в буфер. (Класс Producer)
2.	Созданы несколько потоков-потребителей, которые извлекают элементы из буфера. (Класс Consumer)
3.	Потоки запущены одновременно.
4.	Все элементы, добавленные производителями, корректно извлекаются потребителями.
6. Оба класса Producer и Consumer используют CountDownLatch для синхронизации запуска потоков и уведомления о завершении работы.

Таким образом, потоки могут безопасно добавлять и извлекать элементы из буфера благодаря синхронизации. Методы wait() и notifyAll() в сочетании с synchronized обеспечивает безопасный доступ к буферу (общий ресурс). Кроме того, CountDownLatch предоставляет удобный способ управления потоками.
