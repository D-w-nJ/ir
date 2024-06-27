package thread_sync;

public class ThreadSyncSynchronized {
    private boolean flag = true;

    public synchronized boolean isFlag() {
        return flag;
    }

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void test() {
        new Thread(() -> {
            System.out.println("Thread1 starts");
            int count = 0;
            while (isFlag()) {
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
            System.out.println("Thread2 finished");
            setFlag(false);
        }).start();
    }

    public static void main(String[] args) {
        ThreadSyncSynchronized test = new ThreadSyncSynchronized();
        test.test();

    }
}

/*
결과:

Thread1 starts
Thread2 starts
Thread2 finished
Thread1 finished

 */
