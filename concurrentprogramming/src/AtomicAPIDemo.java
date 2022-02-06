import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

public class AtomicAPIDemo {

    static AtomicLong atomicCounter;
    static AtomicLongArray atomicLongArray = new AtomicLongArray(new long[]{1, 7, 9, 13});
    static LongAdder longAdder = new LongAdder();
    static AtomicReference<Integer> atomicReference = new AtomicReference<>();
    static ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            atomicCounter.incrementAndGet();
                        }
                    }
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }


        int index = 3;
        long item = atomicLongArray.get(index);
        atomicLongArray.set(index, 7);
        atomicLongArray.compareAndSet(index, 7, 1);
        long result = atomicLongArray.addAndGet(index, 10);
        result = atomicLongArray.getAndAdd(index, 10);
        result = atomicLongArray.getAndIncrement(index);
        result = atomicLongArray.incrementAndGet(index);
        result = atomicLongArray.decrementAndGet(index);
        result = atomicLongArray.getAndDecrement(index);


        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();

        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000000; i++) {
                            longAdder.increment();
                        }
                    }
                });
            }
        } finally {
            executorService2.shutdown();
            executorService2.awaitTermination(1, TimeUnit.HOURS);
        }


        ExecutorService executor3 = Executors.newFixedThreadPool(10);
        try {
            for (int i = 0; i < 25; i++) {
                executor3.submit(new Runnable() {
                    @Override
                    public void run() {

                        if (atomicReference.get() == null) {
                            if (atomicReference.compareAndSet(null, 10)) {
                                System.out.println(Thread.currentThread().getName() + " takes first turn");
                            }
                        }
                    }
                });
            }
        } finally {
            executor3.shutdown();
            executor3.awaitTermination(1, TimeUnit.HOURS);
        }


        ExecutorService executor4 = Executors.newFixedThreadPool(10);
        map.put("Test", new AtomicInteger(0));
        executor4.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    map.get("Test").incrementAndGet();
            }
        });
    }
}
