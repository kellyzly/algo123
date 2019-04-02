package kellyzly;

import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;


public class ReetranLockTest {


    private static ExecutorService pool = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) {
        ReetranLockTest test = new ReetranLockTest();
        ReetrantLock_PrintWaitthread lock = test.new ReetrantLock_PrintWaitthread(false);
        for (int i = 0; i < 5; i++) {
            pool.execute(test.new Job(lock));
        }


        pool.shutdown();
    }

    // just to print threads waiting to get the lock
    class ReetrantLock_PrintWaitthread extends ReentrantLock {
        public ReetrantLock_PrintWaitthread(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            return super.getQueuedThreads();
        }
    }

    class Job extends Thread {
        private ReetrantLock_PrintWaitthread lock;

        public Job(ReetrantLock_PrintWaitthread lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            // add by kellyzly   current thread wants to get the lock 5 times
            // fair , current thread name : 0, 1, 2,3, 4, 0, 1, 2, 3,4,...
            // unfair , current thread name: 0 0 0 0 0  1 1 1 1 1 2 2 2 2 2 ...
            for (int i = 0; i < 5; i++) {

                this.lock.lock();
                System.out.println(String.format("Current thread name:%s, other waiting:%s", Thread.currentThread().getName(),
                        lock.getQueuedThreads()));

                this.lock.unlock();
            }
        }
    }
}
