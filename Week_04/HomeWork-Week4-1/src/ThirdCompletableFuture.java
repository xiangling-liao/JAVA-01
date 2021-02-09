import fibo.FiboSum;

import java.util.concurrent.*;

public class ThirdCompletableFuture {

    public static void main(String[] args) {
        Integer result = CompletableFuture.supplyAsync(()->{
            return FiboSum.sum();
        }).join();
        System.out.println("result: " + result);
    }


}
