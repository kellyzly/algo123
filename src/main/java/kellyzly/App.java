package kellyzly;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    private static ExecutorService pool = new ThreadPoolExecutor(10,10, 60L, TimeUnit.SECONDS,
             new ArrayBlockingQueue<Runnable>(10));


      private class SubThread implements Runnable{
        private int id;
        public SubThread(int id){
            this.id = id;
        }
       public void run(){
           System.out.println(id);
       }
    }

    public static void main(String[] args) {
        // 如何实现一个线程池
        // 1. 考虑扩容性
        // 2. task timeout retry
        //下面是个线程池的例子


        App app = new App();
     for(int i=0;i<10;i++){

          pool.execute(app.new SubThread(i));
     }


    }

}
