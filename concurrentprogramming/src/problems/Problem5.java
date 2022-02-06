package problems;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;

class H2O {

    String[] molecule;
    int count;

    H2O() {
        molecule = new String[3];
        count = 0;
    }

    public void hydrogen() throws InterruptedException {
        synchronized (this) {
            while (Collections.frequency(Arrays.asList(molecule), "H") == 2) {
                this.wait();
            }

            molecule[count] = "H";
            count++;

            if (count == 3) {
                for (String element : molecule) {
                    System.out.print(element);
                }
                Arrays.fill(molecule, null);
                count = 0;
            }

            this.notifyAll();
        }
    }

    public void oxygen() throws InterruptedException {
        synchronized (this) {
            while (Collections.frequency(Arrays.asList(molecule), "O") == 1) {
                this.wait();
            }

            molecule[count] = "O";
            count++;

            if (count == 3) {
                for (String element : molecule) {
                    System.out.print(element);
                }
                Arrays.fill(molecule, null);
                count = 0;
            }

            this.notifyAll();
        }
    }
}

class H2OThread extends Thread {

    H2O h20;
    String atom;

    H2OThread(H2O h20, String atom) {
        this.h20 = h20;
        this.atom = atom;
    }

    public void run() {
        if (atom.equals("H")) {
            try {
                h20.hydrogen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (atom.equals("O")) {
            try {
                h20.oxygen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Problem5 {

    public static void main(String[] args) {

        H2O molecule = new H2O();

        Thread t1 = new H2OThread(molecule, "H");
        Thread t2 = new H2OThread(molecule, "O");
        Thread t3 = new H2OThread(molecule, "H");
        Thread t4 = new H2OThread(molecule, "O");

        t2.start();
        t1.start();
        t4.start();
        t3.start();
    }
}
