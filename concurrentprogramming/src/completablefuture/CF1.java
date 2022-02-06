package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CF1 {


    /*

    Thread calls the get() method on our CompletableFuture object, it blocks until the computation is complete.
    We can complete the CompletableFuture using the complete() method.
     */
    private static Future<Integer> getSquareAsynchronously(int num) throws InterruptedException {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);

            // The complete() call will complete this CompetableFuture.
            completableFuture.complete(num * num);
            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(getSquareAsynchronously(2).get());
    }
}
