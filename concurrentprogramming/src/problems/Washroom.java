package problems;

import java.util.concurrent.Semaphore;

/*
There cannot be men and women in the washroom at the same time.
There can never be more than 3 people in the bathroom simultaneously.

 */
class Washroom {

    String inUseBy = "none";
    int countInBathroom = 0;
    Semaphore max = new Semaphore(3);

    void useWashroom(String name) throws InterruptedException {
        System.out.println(name + " using bathroom");
        System.out.println("number of people in bathroom = " + countInBathroom);
        Thread.sleep(5000);
        System.out.println(name + " done using bathroom ");
    }

    void maleUseWashroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals("female")) {
                this.wait();
            }
            inUseBy = "male";
            countInBathroom++;

            max.acquire();
            useWashroom(name);
            max.release();

            countInBathroom--;
            if (countInBathroom == 0) {
                inUseBy = "none";
            }
            this.notifyAll();
        }
    }

    void femaleUseWashroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals("male")) {
                this.wait();
            }
            inUseBy = "female";
            countInBathroom++;

            max.acquire();
            useWashroom(name);
            max.release();

            countInBathroom--;
            if (countInBathroom == 0) {
                inUseBy = "none";
            }
            this.notifyAll();
        }
    }
}

class WashroomDemo {

    public static void main(String args[]) throws InterruptedException {
        final Washroom washroom = new Washroom();

        Thread female1 = new Thread(new Runnable() {
            public void run() {
                try {
                    washroom.femaleUseWashroom("Anjali");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread female2 = new Thread(new Runnable() {
            public void run() {
                try {
                    washroom.femaleUseWashroom("Priya");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread male1 = new Thread(new Runnable() {
            public void run() {
                try {
                    washroom.maleUseWashroom("Dev");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread male2 = new Thread(new Runnable() {
            public void run() {
                try {
                    washroom.maleUseWashroom("Ajay");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread male3 = new Thread(new Runnable() {
            public void run() {
                try {
                    washroom.maleUseWashroom("Pravin");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread male4 = new Thread(new Runnable() {
            public void run() {
                try {
                    washroom.maleUseWashroom("Nitin");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        female1.start();
        female2.start();
        male1.start();
        male2.start();
        male3.start();
        male4.start();

        female1.join();
        female2.join();
        male1.join();
        male2.join();
        male3.join();
        male4.join();

    }
}

