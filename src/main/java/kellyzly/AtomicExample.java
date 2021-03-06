package kellyzly;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    // 核心poolSize
    // maximumPoolSize
    // keepAliveTime: 除了corePoolSize 以外的线程在空闲时候要等待多久被回收
    //当任务数量超过额定工人数量时，将任务缓存在BlockingQueue之中
    private static ExecutorService pool = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) {

        AtomicExample atomicExample = new AtomicExample();
        Counter counter = atomicExample.new Counter();
        int N = 10;
        for (int i = 0; i < N; i++) {
            pool.execute(atomicExample.new AddCounter(counter));
        }

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);


        pool.shutdown();
        System.out.println("value is not 10 because this is synchronized program, count is dirty data");
        System.out.println("count value:" + counter.sync_getCount());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 2);
    }

    class AddCounter implements Runnable {

        private Counter counter;

        public AddCounter(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            counter.sync_increase();
        }
    }


    // Counter 类不是线程安全
    // 线程安全1。.synchronized
    //         2. CAS  每次更新对象之前将内存volatile 变量取出来，和预期的比较， 如果相同，则更新，不同则不更新  AtomicInteger
    //  实现不加锁也能实现锁控制且效率高

    class Counter {
        private int counter;
        private AtomicInteger atomicInteger;

        public Integer getCount() {
            return counter;
        }

        public void increase() {
            System.out.println("Thread name:" + Thread.currentThread().getName());
            counter++;


        }

        public void sync_increase() {
            atomicInteger.incrementAndGet();
        }

        public int sync_getCount() {
            return atomicInteger.get();
        }

    }
}
