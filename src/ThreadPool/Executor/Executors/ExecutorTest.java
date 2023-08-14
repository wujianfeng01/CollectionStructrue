package ThreadPool.Executor.Executors;

import java.util.concurrent.*;

/**
 * Executors工具类创建线程池
 */
public class ExecutorTest {
    public static void main(String[] args) {
        /* 使用Executors工具类创建固定类型的线程池 */
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        /* execute方法 只能执行Runnable方法 无返回值*/
        for (int i = 0; i < 10; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+" "+j);
            });
        }
        System.out.println(threadPool.isShutdown());

        /*  submit方法 可执行runnable和callable任务 有返回值*/
        for (int i = 10; i < 20; i++) {
            int j = i;
            Future<String> submit = threadPool.submit(() -> {
                String res = null;
                if (j % 2 == 0) return res = Thread.currentThread().getName() + " " + j;
                else return null;
            });
            try {
                String s = submit.get();
                System.out.println(s);
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
        /* shutdown线程池，不再接受新的任务，会执行完当前任务和等待队列中的任务 */
        threadPool.shutdown();
        System.out.println(threadPool.isShutdown());
    }
}
