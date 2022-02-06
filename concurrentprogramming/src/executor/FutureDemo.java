package executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

    static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    private int sum(final int n) throws ExecutionException, InterruptedException {

        Future<Integer> f = threadPool.submit(new Callable<Integer>() {

            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 1; i <= n; i++)
                    sum += i;

                return sum;
            }
        });

        return f.get();
    }

    public static void main(String args[]) throws Exception {
        System.out.println("sum :" + new FutureDemo().sum(10));
        threadPool.shutdown();
    }


}
