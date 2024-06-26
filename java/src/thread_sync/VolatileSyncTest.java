package thread_sync;

public class VolatileSyncTest {
    volatile int var = 0;

    public void test() {
        new Thread(() -> {
            System.out.println("Thread1 starts");
            for(int i=0; i<10000; i++){
                var++;
            }
            System.out.println("Thread1 finished");
        }).start();

        new Thread(() -> {
            System.out.println("Thread2 starts");
            for(int i=0; i<10000; i++){
                var++;
            }
            System.out.println("Thread2 finished");
        }).start();

        System.out.println(var);
    }

    public static void main(String[] args) {
        VolatileSyncTest test = new VolatileSyncTest();
        test.test();

    }
}

/*
결과:

Thread1 starts
Thread1 finished
10000
Thread2 starts
Thread2 finished

 */
