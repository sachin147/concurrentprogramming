import java.util.concurrent.CountDownLatch;

class Worker extends Thread {
    private CountDownLatch countDownLatch;

    public Worker(CountDownLatch countDownLatch, String name) {
        super(name);
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Worker " + Thread.currentThread().getName() + " started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Worker  " + Thread.currentThread().getName() + " finished");

        countDownLatch.countDown();
    }
}


class Master extends Thread {
    public Master(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Master " + Thread.currentThread().getName() + " started");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Master " + Thread.currentThread().getName() + " finished");
    }
}

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Worker worker1 = new Worker(countDownLatch, "worker1");
        Worker worker2 = new Worker(countDownLatch, "worker2");

        worker1.start();
        worker2.start();

        countDownLatch.await();

        Master master = new Master("Master");
        master.start();
    }
}
