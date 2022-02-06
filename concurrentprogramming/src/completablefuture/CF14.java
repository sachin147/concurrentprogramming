package completablefuture;

import java.util.concurrent.CompletableFuture;

class CF14 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 50;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 40;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 30;
        });


        final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future2, future3);

        System.out.println(Thread.currentThread().getName());

        try {
            voidCompletableFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("all futures complete");
    }
}
