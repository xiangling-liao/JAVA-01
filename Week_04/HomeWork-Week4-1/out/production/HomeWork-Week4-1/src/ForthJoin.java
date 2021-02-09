import fibo.FiboSum;

public class ForthJoin implements Runnable {
    //use join() to wait for the result
    static int sum;

    @Override
    public void run() {
        sum = FiboSum.sum();
    }

    public static void main(String[] args) throws InterruptedException {
        ForthJoin task = new ForthJoin();
        Thread t = new Thread(task, "t1");

        t.start();
        t.join();
        System.out.println("result: " + ForthJoin.sum);
    }


}
