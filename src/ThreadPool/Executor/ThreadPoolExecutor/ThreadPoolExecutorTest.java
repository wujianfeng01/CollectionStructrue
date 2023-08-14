package ThreadPool.Executor.ThreadPoolExecutor;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        /* 创建固定大小线程池 */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,5,0,
                                                                TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        /* execute方法 只能执行Runnable方法 无返回值*/
        for (int i = 0; i < 100; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+" "+j);
            });
            // 任务等待队列长度
            System.out.println("等待队列长度："+threadPool.getQueue().size());
        }
        /* future任务数组 */
        Future<String>[] submit = new Future[100];

        /*  submit方法可执行runnable和callable任务。
            有返回值，返回Future对象                */
        for (int i = 100; i < 200; i++) {
            int j = i;
            // 线程池返回FutureTask对象放入Future数组
            submit[i-100] = threadPool.submit(() -> {
                String res = null;
                if (j % 2 == 0) return res = Thread.currentThread().getName() + " " + j;
                else return null;
            });
            System.out.println("等待队列长度："+threadPool.getQueue().size());
        }

        /* 输出Future任务结果 */
        for (Future f : submit) {
            try {
                Object res = f.get();     // 线程未执行完成则阻塞
                System.out.println(res);
            }catch (Exception ex){
                System.out.println(ex);
            }

        }
        /* shutdown线程池，不再接受新的任务，会执行完当前任务和等待队列中的任务 */
        threadPool.shutdown();
        /* 判断线程池是否关闭 */
        System.out.println(threadPool.isShutdown());
    }
}
