import fibo.FiboSum;

import java.util.concurrent.*;

public class SecondFutureTask {

    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return FiboSum.sum();
            }
        });

        threadPoolExecutor.submit(futureTask);
        threadPoolExecutor.shutdown();
        try {
            System.out.println("result: " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
