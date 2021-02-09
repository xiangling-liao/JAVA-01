import fibo.FiboSum;

import java.util.concurrent.*;

public class FirstFuture {

    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPoolExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return FiboSum.sum();
            }
        });

        threadPoolExecutor.shutdown();
        try {
            System.out.println("result: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
