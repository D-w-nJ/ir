package thread_sync;

public class SynchronizedSyncTest {
    private int var = 0;

    public synchronized int getVar() {
        return var;
    }

    public synchronized void increment() {
        var += 1;
    }

    public void test() {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1 starts");
            for (int i = 0; i < 10000; i++) {
                increment();
            }
            System.out.println("Thread1 finished");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2 starts");
            for (int i = 0; i < 10000; i++) {
                increment();
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

        System.out.println("var = " + getVar());
    }

    public static void main(String[] args) {
        SynchronizedSyncTest test = new SynchronizedSyncTest();
        test.test();
    }
}
