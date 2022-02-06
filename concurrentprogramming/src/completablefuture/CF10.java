package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class CF10 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }*/
            System.out.println(Thread.currentThread().getName());
            return 50;
        });


        /*
        if we don’t want to return anything from our callback function.
        takes a Consumer<T> as a parameter and returns a CompletableFuture<Void>.
         */
        CompletableFuture<Void> voidCompletableFuture = future.thenAccept(num -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("The value is " + num);
        });

        try {
            System.out.println(voidCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
