import fibo.FiboSum;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SeventhLock {
    static int res = 0;
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                res = FiboSum.sum();
                try {
                    // prevent notify signal is missed by main thread
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    //have to have notify() invoked from same monitor `synchronized(lock)`
                    lock.notify();
                }
            }
        });

        t.start();
        synchronized (lock) {
            lock.wait();
        }

        System.out.println("result: " + res);
    }


}
