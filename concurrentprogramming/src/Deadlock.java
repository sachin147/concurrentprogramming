class Demonstration {

    public static void main(String args[]) {
        Deadlock deadlock = new Deadlock();
        try {
            deadlock.testDeadlock();
        } catch (InterruptedException ex) {
        }
    }
}

class Deadlock {

    private int counter = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    Runnable incrementer = new Runnable() {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    increment();
                    System.out.println("increment " + i);
                }
            } catch (InterruptedException ex) {
            }
        }
    };

    Runnable decrementer = new Runnable() {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    decrement();
                    System.out.println("decrement " + i);
                }
            } catch (InterruptedException ex) {
            }

        }
    };

    public void testDeadlock() throws InterruptedException {

        Thread thread1 = new Thread(incrementer);
        Thread thread2 = new Thread(decrementer);

        thread1.start();
        Thread.sleep(100);
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("complete " + counter);
    }

    void increment() throws InterruptedException {
        synchronized (lock1) {
            System.out.println("acquired lock1");
            Thread.sleep(1000);
            synchronized (lock2) {
                counter++;
            }
        }
    }

    void decrement() throws InterruptedException {
        synchronized (lock2) {
            System.out.println("acquired lock2");
            Thread.sleep(1000);
            synchronized (lock1) {
                counter--;
            }
        }
    }
}