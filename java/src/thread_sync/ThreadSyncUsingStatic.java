package thread_sync;

public class ThreadSyncUsingStatic {
    static boolean flag = true;

    public void test() {
        new Thread(() -> {
            System.out.println("Thread1 starts");
            int count = 0;
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (ThreadSyncUsingStatic.flag) {
                count++;
            }
            System.out.println("Thread1 finished");
        }).start();

        new Thread(() -> {
            System.out.println("Thread2 starts");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
            // ThreadSyncUsingStatic.setFlag();
            System.out.println("flag in thread = " + flag);
            System.out.println("Thread2 finished");
        }).start();
    }

    public static void main(String[] args) {
        ThreadSyncUsingStatic test = new ThreadSyncUsingStatic();
        test.test();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadSyncUsingStatic.flag = " + ThreadSyncUsingStatic.flag);
    }
}

/*
결과:

Thread1 starts
Thread2 starts
Thread2 finished

...

 */
