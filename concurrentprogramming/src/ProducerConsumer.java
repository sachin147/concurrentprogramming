class ProducerConsumer {

    public static void main(String args[]) throws Exception {
        final BlockingQueue<Integer> q = new BlockingQueue<Integer>(5);

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        q.enqueue(new Integer(i));
                        System.out.println("Thread 1 enqueued " + i);
                    }
                } catch (InterruptedException ie) {

                }
            }
        });


        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("Thread 2 dequeued: " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });


        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("Thread 3 dequeued: " + q.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });


        t1.start(); //enquue
        Thread.sleep(4000);
        t2.start(); //dequeue

        t2.join(); //dequeue //t.join() will make sure that t is terminated before the next instruction is executed by the program, starts t3 after t2 has died.

        t3.start(); //dequeue
        t1.join(); //enqueue
        t3.join(); //dequeue
    }
}

class BlockingQueue<T> {

    T[] array;
    int size = 0;
    int capacity;
    int head = 0;
    int tail = 0;
    Object lock = new Object();

    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        array = (T[]) new Object[capacity]; ////  casting results in a warning
        this.capacity = capacity;
    }


    public void enqueue(T item) throws InterruptedException {
        synchronized (lock) {

            System.out.println("enqueue - tail : " + tail + " , size : " + size + " , capacity : " + capacity);
            System.out.println();

            while (size == capacity) {
                System.out.println("queue full - waiting...");
                lock.wait();
            }

            if (tail == capacity) {
                tail = 0;
            }

            array[tail] = item;
            size++;
            tail++;

            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {

        T item = null;
        synchronized (lock) {

            System.out.println("dequeue - head : " + head + " , size : " + size + ", capacity : " + capacity);
            System.out.println();

            while (size == 0) {
                System.out.println("queue empty - waiting....");
                lock.wait();
            }

            if (head == capacity) {
                head = 0;
            }

            item = array[head];
            array[head] = null;
            head++;
            size--;


            lock.notifyAll();
        }

        return item;
    }
}