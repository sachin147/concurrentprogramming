package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CF9 {

    public static void main(String args[]) {

        ExecutorService executor = Executors.newFixedThreadPool(5);


        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 50;
        });

        /*
        overloaded version of thenApplyAsync()
         */
        CompletableFuture<Integer> resultFuture = future.thenApplyAsync(num -> {
            System.out.println(Thread.currentThread().getName());
            return num * 2;
        }, executor);

        try {
            System.out.println(resultFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
