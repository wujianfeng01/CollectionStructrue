package ThreadMethod;

/**
 * join() 方法是 Thread 类提供的方法，用于线程之间的同步。
 * 当在一个线程中调用另一个线程的 join() 方法时，调用线程会等待被调用的线程执行完成。
 * 调用线程会阻塞，并释放之前获得的锁，直到被调用的线程执行完毕或超时。
 * join() 方法的主要目的是为了等待另一个线程完成，通常用于确保某个线程的操作在其他线程继续之前完成。
 */
public class ThreadObjectJoinMethodTest {
    public static void main(String[] args) {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj){
                try {
                    System.out.println("Thread is doing some work...");
                    Thread.sleep(2000); // 模拟线程工作
                    System.out.println("Thread finished work.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        synchronized (obj){
            thread.start();
            try {
                System.out.println("Main thread is waiting for the other thread to finish...");
                thread.join(); // 主线程等待 thread 线程完成
                System.out.println("Main thread continues after the other thread finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
