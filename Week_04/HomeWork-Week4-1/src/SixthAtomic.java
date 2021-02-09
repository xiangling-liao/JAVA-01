import fibo.FiboSum;

import java.util.concurrent.atomic.AtomicInteger;

public class SixthAtomic {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger res = new AtomicInteger(-1);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                res.set(FiboSum.sum());
            }
        });

        t.start();

        // Atomic get() is non-blocking method
        while (res.get() < 0) {}

        System.out.println("result: " + res.get());
    }


}
