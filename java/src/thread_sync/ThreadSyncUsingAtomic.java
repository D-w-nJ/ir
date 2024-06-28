package thread_sync;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadSyncUsingAtomic {
    AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public void test() {
        new Thread(() -> {
            System.out.println("Thread1 starts");
            long count = 0;
            while (atomicBoolean.get()) {
                count++;
            }
            System.out.println("count = " + count);
            System.out.println("Thread1 finished");
        }).start();

        new Thread(() -> {
            System.out.println("Thread2 starts");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 finished");
            atomicBoolean.set(false);
        }).start();
    }

    public static void main(String[] args) {
        ThreadSyncUsingAtomic test = new ThreadSyncUsingAtomic();
        test.test();

    }
}

/*
결과:

Thread1 starts
Thread2 starts
Thread2 finished
count = 240860460
Thread1 finished

 */
