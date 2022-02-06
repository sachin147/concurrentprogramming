package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class CF7 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            //also run by commenting below sleep code
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 50;
        });


        // Calling thenApply() which takes a Function as parameter.
        CompletableFuture<Integer> resultFuture = future.thenApply(num -> {
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
/*

the same thread executes the code in supplyAsync() and thenApply().
if supplyAsync() completes very fast then thenApply() executes in the main thread.
 */