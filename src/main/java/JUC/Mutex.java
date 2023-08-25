package JUC;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义自旋锁Mutex
 * 通过使用内部类Sync对象实现，Sync继承于AQS队列。
 */
public class Mutex {
    public final Sync  sync;

    public Mutex() {
        this.sync = new Sync();
    }

    /**
     * 内部类Sync
     * 继承AQS
     */
    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            while (!compareAndSetState(0,arg));    // 获取锁，自旋操作
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(isHeldExclusively()){
                int state = getState();
                state = state - arg;
                while (!compareAndSetState(1,state)); // 释放锁，自旋操作
                setExclusiveOwnerThread(null);
                return true;
            }else throw new IllegalMonitorStateException();

        }

        /**
         * 判断锁是否被独占
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 判断锁是否被当前线程所持有
         * @return
         */
        public boolean isHeldByCurrentThread(){
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        /**
         * 获取独占锁的持有者线程
         */
        public void getOwnerThread(){
            System.out.println(getExclusiveOwnerThread());
        }
    }

    /**
     * 加锁
     */
    public void lock(){
        sync.acquire(1);   // acquire AQS中final修饰方法
    }

    /**
     * 释放锁
     */
    public void unLock(){
        sync.release(1);   // release AQS中final修饰方法
    }
    // 锁是否被当前线程持有



    public static void main(String[] args) {
        Mutex mutex = new Mutex();

        Thread thread1 = new Thread(() -> {
            mutex.lock();
            try {
                System.out.println("Thread 1 acquired the mutex.");
                System.out.println(mutex.sync.isHeldByCurrentThread());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                mutex.unLock();
                System.out.println("Thread 1 released the mutex.");
            }
        });

        Thread thread2 = new Thread(() -> {
            mutex.lock();
            try {
                System.out.println("Thread 2 acquired the mutex.");
                System.out.println(mutex.sync.isHeldByCurrentThread());
            } finally {
                mutex.unLock();
                System.out.println("Thread 2 released the mutex.");
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
