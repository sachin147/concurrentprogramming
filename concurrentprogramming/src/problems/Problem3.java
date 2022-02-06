package problems;


class FizzBuzz {

    private int n;
    private int num = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() {
        synchronized (this) {
            while (num <= n) {
                if (num % 3 == 0 && num % 5 != 0) {
                    num++;
                    System.out.println("fizz");
                    this.notifyAll();
                } else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void buzz() {
        synchronized (this) {
            while (num <= n) {
                if (num % 3 != 0 && num % 5 == 0) {
                    System.out.println("buzz");
                    num++;
                    this.notifyAll();
                } else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void fizzbuzz() {
        synchronized (this) {
            while (num <= n) {
                if (num % 15 == 0) {
                    System.out.println("fizzbuzz");
                    num++;
                    this.notifyAll();
                } else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void number() {
        synchronized (this) {
            while (num <= n) {
                if (num % 3 != 0 && num % 5 != 0) {
                    System.out.println(num);
                    num++;
                    this.notifyAll();
                } else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class FizzBuzzThread extends Thread {

    FizzBuzz fizzBuzz;
    String method;

    FizzBuzzThread(FizzBuzz fizzBuzz, String method) {
        this.fizzBuzz = fizzBuzz;
        this.method = method;
    }

    public void run() {
        if (method.equals("fizz")) {
            fizzBuzz.fizz();
        } else if (method.equals("buzz")) {
            fizzBuzz.buzz();
        } else if (method.equals("fizzbuzz")) {
            fizzBuzz.fizzbuzz();
        } else if (method.equals("number")) {
            fizzBuzz.number();
        }
    }
}

public class Problem3 {

    public static void main(String[] args) {
        FizzBuzz obj = new FizzBuzz(15);

        Thread t1 = new FizzBuzzThread(obj, "fizz");
        Thread t2 = new FizzBuzzThread(obj, "buzz");
        Thread t3 = new FizzBuzzThread(obj, "fizzbuzz");
        Thread t4 = new FizzBuzzThread(obj, "number");

        t2.start();
        t1.start();
        t4.start();
        t3.start();
    }
}
