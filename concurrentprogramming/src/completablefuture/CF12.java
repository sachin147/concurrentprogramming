package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class CF12 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 50;
        });

        /*
        if we use theApply() below, the result will be nested in CompletableFuture, i.e., CompletableFuture<CompletableFuture<Integer>>.
        always use thenCompose() if you need a flat result.
         */
        CompletableFuture<Integer> resultFuture = future.thenCompose(num ->
                CompletableFuture.supplyAsync(() -> num * 2));

        try {
            System.out.println(resultFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
