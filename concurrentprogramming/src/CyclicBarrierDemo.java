import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class MyTask implements Runnable {

    private CyclicBarrier barrier;

    public MyTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
        } catch (InterruptedException ex) {
        } catch (BrokenBarrierException ex) {
        }
    }

}

public class CyclicBarrierDemo {
    public static void main(String args[]) {

        final CyclicBarrier cb = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("all threads arrived at the barrier.");
            }
        });

        Thread t1 = new Thread(new MyTask(cb), "Thread 1");
        Thread t2 = new Thread(new MyTask(cb), "Thread 2");
        Thread t3 = new Thread(new MyTask(cb), "Thread 3");
        Thread t4 = new Thread(new MyTask(cb), "Thread 4");
        Thread t5 = new Thread(new MyTask(cb), "Thread 5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
