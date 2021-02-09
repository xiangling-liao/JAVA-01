import fibo.FiboSum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TwelfthCondition {
    static int res = 0;
    final static Lock lock = new ReentrantLock();
    static Condition readyForMain = lock.newCondition();
//    public void assignSum() {
//        lock.lock();
//        res = FiboSum.sum();
//        readyForMain.signal();
//        lock.unlock();
//    }
//
//    public void waitForThread() throws InterruptedException {
//        lock.lock();
//        readyForMain.await();
//        lock.unlock();
//    }
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
       // TwelfthCondition obj = new TwelfthCondition();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                //obj.assignSum();
                res = FiboSum.sum();
                // await/signal have to be wrapped around lock()/unlock() of a same lock
                lock.lock();
                readyForMain.signal();
                lock.unlock();
            }
        });

        t.start();

        //obj.waitForThread();
        lock.lock();
        readyForMain.await();
        lock.unlock();
        System.out.println("result: " + res);
    }


}
