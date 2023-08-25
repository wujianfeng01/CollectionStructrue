package JUC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程CAS操作，保证操作的原子性
 */
public class CASExample {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int expect;
        int update;
        do {
            expect = counter.get(); // 获取当前值
            update = expect + 1;    // 计算新值
            // CAS操作，如果当前值等于预期值expect，则将新值update写入，否则重试
        } while (!counter.compareAndSet(expect, update));
    }

    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) {
        final CASExample example = new CASExample();
        // 创建多个线程并发地调用increment方法
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.increment();
                }
            }).start();
        }
        // 阻塞主线程，等待所有线程执行完毕
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出最终结果，一定是5000
        System.out.println("Final counter value: " + example.getCounter());
    }
}

