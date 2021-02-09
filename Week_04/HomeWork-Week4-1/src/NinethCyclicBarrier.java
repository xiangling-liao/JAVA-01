import fibo.FiboSum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class NinethCyclicBarrier {
    static int res = 0;
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                res = FiboSum.sum();
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException|InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t.start();
        cyclicBarrier.await();

        System.out.println("result: " + res);
    }


}
