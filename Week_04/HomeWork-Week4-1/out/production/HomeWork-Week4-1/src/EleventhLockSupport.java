import fibo.FiboSum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

public class EleventhLockSupport {
    static int res = 0;
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Object barrier = new Object();
        Thread mainThread = Thread.currentThread();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                res = FiboSum.sum();
                LockSupport.unpark(mainThread);
            }
        });

        t.start();

        LockSupport.park();
        System.out.println("result: " + res);
    }


}
