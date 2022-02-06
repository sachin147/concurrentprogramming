package forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

class CustomRecursiveAction extends RecursiveAction {

    private int start;
    private int end;
    private Double[] weights;

    public CustomRecursiveAction(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    protected void compute() {
        if(end-start <= 3)
            for(int i=start; i<end; i++) {
                weights[i] = (double)new Random().nextInt(100);
                System.out.println("Animal Weighed: "+i);
            }
        else {
            int middle = start+((end-start)/2);
            System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
            invokeAll(new CustomRecursiveAction(weights,start,middle),
                    new CustomRecursiveAction(weights,middle,end));
        }
    }
}

public class RecursiveActionDemo {

    public static void main(String[] args) {

        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new CustomRecursiveAction(weights,0,weights.length);
        ForkJoinPool pool  = new ForkJoinPool();
        pool.invoke(task);

        System.out.println();
        System.out.print("Weights: ");
        Arrays.asList(weights).stream().forEach(
                d -> System.out.print(d.intValue()+" "));

    }
}