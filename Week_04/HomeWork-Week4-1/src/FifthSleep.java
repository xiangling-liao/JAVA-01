import fibo.FiboSum;

public class FifthSleep implements Runnable {
    // let main thread sleep for waiting for the result
    static int sum;

    @Override
    public void run() {
        sum = FiboSum.sum();
    }

    public static void main(String[] args) throws InterruptedException {
        FifthSleep task = new FifthSleep();
        Thread t = new Thread(task, "t1");

        t.start();
        Thread.currentThread().sleep(1000);
        System.out.println("result: " + FifthSleep.sum);
    }


}
