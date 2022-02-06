import jdk.nashorn.internal.ir.Block;

public class ThreadSafeSingleton {

    //volatile for extra safety, corner case in memory model in case of
    //semi constructed object
    private static volatile ThreadSafeSingleton threadSafeSingleton;

    /*eager initialisation
    private static final ThreadSafeSingleton threadSafeSingleTon = new ThreadSafeSingleton();*/



    private ThreadSafeSingleton() {
    }

    /*thread safe, but expensive
    public synchronized  static ThreadSafeSingleton getInstance()  {}*/

    public static ThreadSafeSingleton getInstance() {

        /*lazy initialisation, not thread safe
        if(threadSafeSingleton == null) {
            threadSafeSingleton = new ThreadSafeSingleton()
        }*/


        //double check lock
        //synchronize only when initializing the singleton instance
        //first check is without synchronization and the second is with .
        //once a singleton instance has been initialized,
        //all future invocations of the getInstance() method don't pass the first null check
        //and return the instance without getting involved in synchronization.
        if(threadSafeSingleton == null) {
            synchronized (ThreadSafeSingleton.class) {

                if(threadSafeSingleton == null) {
                    threadSafeSingleton = new ThreadSafeSingleton();
                }
            }
        }

        return threadSafeSingleton;
    }
}
