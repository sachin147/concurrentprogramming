package executor;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceDemo {

    private void completionServiceExample() throws Exception {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> service = new ExecutorCompletionService<>(threadPool);

        int count = 10;

        for (int i = 0; i < count; i++) {
            int finalI = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            }, new Integer(i));
        }


        while (count != 0) {
            Future<Integer> f = service.poll();
            if (f != null) {
                System.out.println("Thread" + f.get() + " got done.");
                count--;
            }
        }

        threadPool.shutdown();
    }

    public static void main( String args[] ) throws Exception {
        new CompletionServiceDemo().completionServiceExample();
    }

}
