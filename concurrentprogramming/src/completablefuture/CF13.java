package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class CF13 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 50;
        });


        /*
        if we want to execute two independent Futures and do something with their results, we can use the thenCombine() method.
        accepts a Future and a BiFunction to process both results.
         */
        CompletableFuture<Integer> resultFuture = future.thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 20;
                }),
                (num1, num2) -> num1 + num2);

        System.out.println(Thread.currentThread().getName());

        try {
            System.out.println(resultFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
