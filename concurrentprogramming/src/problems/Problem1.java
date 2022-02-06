package problems;

class PrintInOrder {

    int count;

    public PrintInOrder() {
        count = 1;
    }

    public void printFirst() {
        synchronized (this) {
            System.out.println("first thread");
            count++;
            this.notifyAll();
        }
    }

    public void printSecond() throws InterruptedException {
        synchronized (this) {
            while (count != 2) {
                this.wait();
            }
            System.out.println("second thread");
            count++;
            this.notifyAll();
        }
    }

    public void printThird() throws InterruptedException {
        synchronized (this) {
            while (count != 3) {
                this.wait();
            }
            System.out.println("third thread");
            this.notifyAll();
        }
    }
}
/*

//using countdown latch
class OrderedPrinting {
    CountDownLatch latch1;
    CountDownLatch latch2;

    public OrderedPrinting()
    {
        latch1 = new CountDownLatch(1);
        latch2 = new CountDownLatch(1);
    }

    public void printFirst() throws InterruptedException
    {
        System.out.println("First");
        latch1.countDown();
    }

    public void printSecond() throws InterruptedException
    {
        latch1.await();
        System.out.println("Second");
        latch2.countDown();
    }

    public void printThird() throws InterruptedException
    {
        latch2.await();
        System.out.println("Third");
    }
}
*/

class OrderPrintThread extends Thread {

    private PrintInOrder printInOrder;
    private String method;

    public OrderPrintThread(PrintInOrder printInOrder, String method) {
        this.printInOrder = printInOrder;
        this.method = method;
    }

    public void run() {
        if (method.equals("first")) {
            printInOrder.printFirst();
        } else if (method.equals("second")) {
            try {
                printInOrder.printSecond();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                printInOrder.printThird();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Problem1 {
    public static void main(String[] args) {
        PrintInOrder obj = new PrintInOrder();

        OrderPrintThread t1 = new OrderPrintThread(obj, "first");
        OrderPrintThread t2 = new OrderPrintThread(obj, "second");
        OrderPrintThread t3 = new OrderPrintThread(obj, "third");

        t2.start();
        t3.start();
        t1.start();

    }
}