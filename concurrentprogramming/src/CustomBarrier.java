import java.util.concurrent.CyclicBarrier;

class CustomBarrier {

    int totalThreads;
    int count = 0;
    int releasedCount = 0;

    CustomBarrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }


    /*public synchronized void await() throws InterruptedException {

        count++;

        if (count == totalThreads) {
            count = 0;
            notifyAll();
        } else {
            while (count < totalThreads) {
                wait();
            }
        }
    }*/

    public synchronized void await() throws InterruptedException {

        System.out.println("count : " + count);

        if (count == totalThreads) {
            wait();
        }


        count++;

        if (count == totalThreads) {
            System.out.println(count + " == " + totalThreads + " . release all threads");
            notifyAll();
            releasedCount = totalThreads;
        } else {
            while (count < totalThreads) {
                System.out.println(count + " < " + totalThreads + " . waiting at barrier....");
                wait();
            }
        }

        releasedCount--;
        System.out.println("released count  : " + releasedCount);

        if (releasedCount == 0) {
            System.out.println("all threads released . reset count & notify all.");
            count = 0;
            notifyAll();
        }
    }
}


class CustomBarrierDemo {

    public static void main(String args[]) throws Exception {
        final CustomBarrier barrier = new CustomBarrier(3);

        Thread p1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("Thread 1");
                    barrier.await();
                    System.out.println("Thread 1");
                    barrier.await();
                    System.out.println("Thread 1");
                    barrier.await();
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread p2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(500);
                    System.out.println("Thread 2");
                    barrier.await();
                    Thread.sleep(500);
                    System.out.println("Thread 2");
                    barrier.await();
                    Thread.sleep(500);
                    System.out.println("Thread 2");
                    barrier.await();
                } catch (InterruptedException ie) {
                }
            }
        });

        Thread p3 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1500);
                    System.out.println("Thread 3");
                    barrier.await();
                    Thread.sleep(1500);
                    System.out.println("Thread 3");
                    barrier.await();
                    Thread.sleep(1500);
                    System.out.println("Thread 3");
                    barrier.await();
                } catch (InterruptedException ie) {
                }
            }
        });

        p1.start();
        p2.start();
        p3.start();

        p1.join();
        p2.join();
        p3.join();
    }
}
