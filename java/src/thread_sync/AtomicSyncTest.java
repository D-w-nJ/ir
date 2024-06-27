package thread_sync;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSyncTest {
    AtomicInteger var = new AtomicInteger(0);

    public void test() {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1 starts");
            for (int i = 0; i < 10000; i++) {
                var.incrementAndGet();
            }
            System.out.println("Thread1 finished");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2 starts");
            for (int i = 0; i < 10000; i++) {
                var.incrementAndGet();
            }
            System.out.println("Thread2 finished");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("var = " + var);
    }

    public static void main(String[] args) {
        AtomicSyncTest test = new AtomicSyncTest();
        test.test();

    }
}


/*
결과:

Thread1 starts
Thread2 starts
Thread2 finished
Thread1 finished
var = 20000

 */
