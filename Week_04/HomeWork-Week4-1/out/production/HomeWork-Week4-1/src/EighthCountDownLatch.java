import fibo.FiboSum;

import java.util.concurrent.CountDownLatch;

public class EighthCountDownLatch {
    static int res = 0;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                res = FiboSum.sum();
                countDownLatch.countDown();
            }
        });

        t.start();
        countDownLatch.await();

        System.out.println("result: " + res);
    }


}
