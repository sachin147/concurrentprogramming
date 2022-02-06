public class ThreadLocalDemo {
    static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

    public static void main(String args[]) throws Exception {

        Thread[] tasks = new Thread[100];

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    increment();
                }
                System.out.println(counter.get());
            });
            tasks[i] = t;
            t.start();
        }

        for (int i = 0; i < 100; i++) {
            tasks[i].join();
        }

        System.out.println(counter.get());
    }

    static void increment() {
        counter.set(counter.get() + 1);
    }
}


