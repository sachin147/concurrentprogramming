package problems;

class FooBar {
    int n;
    boolean flag;

    FooBar(int n) {
        this.n = n;
        flag = false;
    }

    void foo() {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("foo");
                flag = !flag;
                this.notifyAll();
            }
        }
    }

    void bar() {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (!flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("bar");
                flag = !flag;
                this.notifyAll();
            }
        }
    }

}

class FooBarThread extends Thread {

    private FooBar fooBar;
    private String method;

    FooBarThread(FooBar fooBar, String method) {
        this.fooBar = fooBar;
        this.method = method;
    }

    public void run() {
        if (method.equals("foo")) {
            fooBar.foo();
        }
        if (method.equals("bar")) {
            fooBar.bar();
        }
    }

}

public class Problem2 {

    public static void main(String[] args) {

        FooBar fooBar = new FooBar(3);

        Thread t1 = new FooBarThread(fooBar, "foo");
        Thread t2 = new FooBarThread(fooBar, "bar");

        t2.start();
        t1.start();

    }
}
