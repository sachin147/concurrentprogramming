package executor;

import java.util.concurrent.Executor;

class MyExecutor implements Executor {
    public void execute(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class MyTask implements Runnable {
    public void run() {
        System.out.println("Mytask is running ");
    }
}

public class ExecutorDemo {

    public static void main(String args[]) {
        MyExecutor myExecutor = new MyExecutor();
        MyTask myTask = new MyTask();
        myExecutor.execute(myTask);
    }

}
