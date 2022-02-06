package completablefuture;

import java.util.concurrent.CompletableFuture;

class CF16 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 30);

        //The first completed future will be returned.
        CompletableFuture<Object> firstCompletedFuture = CompletableFuture.anyOf(future1, future2, future3);

        try {
            System.out.println(" first completed future " + firstCompletedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("executed after any of the futures complete.");
    }
}
