import fibo.FiboSum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class TenthSemaphore {
    static int res = 0;
    final static int parties = 1;
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final Semaphore semaphore = new Semaphore(parties);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                res = FiboSum.sum();
                semaphore.release();
            }
        });

        semaphore.acquire();
        t.start();

        semaphore.acquire();
        System.out.println("result: " + res);
        semaphore.release();
    }


}
