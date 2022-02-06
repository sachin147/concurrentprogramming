package completablefuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class CF15 {

    public static void main(String args[]) {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 50);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 40);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 30);

        Stream<CompletableFuture<Integer>> stream = Stream.of(future1, future2, future3);

        //join() method to combine the result of all futures.
        Stream<Integer> integerStream = stream.map(CompletableFuture::join);

        Optional<Integer> maxElement = integerStream.max(Integer::compareTo);

        System.out.println(maxElement);

    }
}
