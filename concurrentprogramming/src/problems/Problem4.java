package problems;

import java.util.concurrent.Semaphore;

class ZeroEvenOdd {

    private int n;
    private Semaphore zeroSemaphore, oddSemaphore, evenSemaphore;

    public ZeroEvenOdd(int n) {
        this.n = n;

        /*The argument to the Semaphore instance is the number of "permits" that are available.
        It can be any integer, not just 0 or 1.

        This value may be negative, in which case releases must occur before any acquires will be granted.
        */

        /*
        A semaphore initialized to one, and which is used such that
        it only has at most one permit available, can serve as a mutual exclusion lock.
        This is more commonly known as a binarysemaphore
         */
        //first acquire() call will succeed as intialized with 1.
        zeroSemaphore = new Semaphore(1);

        //all acquire() calls will be blocked initially initialized with 0
        oddSemaphore = new Semaphore(0);

        //all acquire() calls will be blocked initially initialized with 0
        evenSemaphore = new Semaphore(0);
    }

    public void zero() throws InterruptedException {
        for (int i = 0; i < n; ++i) {
            zeroSemaphore.acquire();
            System.out.print(0);

            if (i % 2 == 0) {
                //as we just printed even
                oddSemaphore.release();
            } else {
                //as we just printed odd
                evenSemaphore.release();
            }
        }
    }

    public void even() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSemaphore.acquire();
            System.out.print(i);
            zeroSemaphore.release();
        }
    }

    public void odd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSemaphore.acquire();
            System.out.print(i);
            zeroSemaphore.release();
        }
    }
}

class ZeroEvenOddThread extends Thread {

    ZeroEvenOdd zeroEvenOdd;
    String method;

    ZeroEvenOddThread(ZeroEvenOdd zeroEvenOdd, String method) {
        this.zeroEvenOdd = zeroEvenOdd;
        this.method = method;
    }

    public void run() {
        if (method.equals("zero")) {
            try {
                zeroEvenOdd.zero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (method.equals("even")) {
            try {
                zeroEvenOdd.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (method.equals("odd")) {
            try {
                zeroEvenOdd.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Problem4 {

    public static void main(String[] args) {

        ZeroEvenOdd zeo = new ZeroEvenOdd(5);

        Thread t1 = new ZeroEvenOddThread(zeo, "zero");
        Thread t2 = new ZeroEvenOddThread(zeo, "even");
        Thread t3 = new ZeroEvenOddThread(zeo, "odd");

        t2.start();
        t1.start();
        t3.start();

    }
}
