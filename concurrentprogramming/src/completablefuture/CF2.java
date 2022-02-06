package completablefuture;

import java.util.concurrent.CompletableFuture;

/*

If we are sure about the result of computation,
we can use the static completedFuture() method with an argument that represents a result of this computation.

The get() method of the Future will never block.
 */
class CF2 {


    public static void main(String args[]) {
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("Hello World");
        try {
            System.out.println(completableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

