package ThreadMethod;

/**
 * 死锁测试
 * 证明线程的blocked状态会保持之前获取的锁。
 * 一个线程在同步块中尝试获取对象的锁，但锁已经被其他线程持有时，它会进入阻塞状态。
 * 一旦持有锁的线程释放了锁，Java虚拟机会从处于阻塞状态的线程中选择一个，并使其尝试重新获取锁。
 * 这种操作是由Java虚拟机内部的调度器负责的。
 */
public class DeadLockTest {
    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(()->{                        // thread1，锁获取顺序 lock1->lock2
            synchronized (lock1){               // 加锁lock1
                System.out.println("Thread1 get lock1......");
                try {
                    Thread.sleep(1000);
                    synchronized (lock2){       // 加锁lock2失败->阻塞
                        System.out.println("Thread1 get lock2......");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{               // thread2， 锁获取顺序 lock2->lock1
            synchronized (lock2){      // 加锁lock2成功
                System.out.println("Thread2 get lock2......");
                synchronized (lock1){  // 加锁lock1失败->阻塞
                    System.out.println("Thread2 get lock1......");
                }
            }
        }).start();

        Thread.sleep(5000);
    }
}
