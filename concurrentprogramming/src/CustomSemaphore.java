class CustomSemaphore {

    int usedPermits = 0;
    int maxPermit;

    public CustomSemaphore(int maxPermit) {
        this.maxPermit = maxPermit;
    }

    /*
    wait() should always be used with a while loop that checks for a condition
     */

    void acquire(int i) throws InterruptedException {
        synchronized (this) {

            System.out.println("acquire " + i);

            while (usedPermits == maxPermit) {
                System.out.println(usedPermits + " == " + maxPermit + " . all permits exhausted . wait");

                this.wait();
            }

            notify(); //order matters
            usedPermits++;

            System.out.println("used permits : " + usedPermits);
        }
    }

    void release(int i) throws InterruptedException {
        synchronized (this) {

            System.out.println("release " + i);

            while (usedPermits == 0) {
                System.out.println(usedPermits + " == " + 0 + "  . no permit in use available to release . wait");

                this.wait();
            }
            usedPermits--;

            System.out.println("used permits : " + usedPermits);

            notify();
        }
    }

}

class Demo {
    public static void main(String[] args) throws InterruptedException {

        CustomSemaphore customSemaphore = new CustomSemaphore(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        customSemaphore.acquire(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        customSemaphore.release(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();
    }
}
