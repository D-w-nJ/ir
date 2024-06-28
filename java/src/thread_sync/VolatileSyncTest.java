package thread_sync;

public class VolatileSyncTest {
    volatile int var = 0;

    public void test(){
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1 starts");
            for(int i=0; i<10000; i++){
                var++;
            }
            System.out.println("Thread1 finished");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2 starts");
            for(int i=0; i<10000; i++){
                var++;
            }
            System.out.println("Thread2 finished");
        });

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

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
