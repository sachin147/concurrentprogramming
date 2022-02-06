package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class CF11 {

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
        difference between thenAccept() and thenRun() is that the
        thenAccept() method has access to the result of the CompletableFuture on which it is attached.
        thenRun() doesn’t have access to the Future’s result.
         */
        future.thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Hello");
        });
    }
}
