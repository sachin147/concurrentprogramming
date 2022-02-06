package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CF4 {

    public static void main(String args[]) {

        Executor executor = Executors.newFixedThreadPool(5);

        // Passing a runnable and executor as parameter to runAsync() method.
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("Doing some processing");
        }, executor);

        System.out.println("This will print immediately");

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("This will print after 5 seconds");

    }
}
