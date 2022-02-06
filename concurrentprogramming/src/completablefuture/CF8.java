package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
all the operations should be executed by a different thread
 */
class CF8 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 50;
        });

        CompletableFuture<Integer> resultFuture = future.thenApplyAsync(num -> {
            System.out.println(Thread.currentThread().getName());
            return num * 2;
        });

        try {
            System.out.println(resultFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
