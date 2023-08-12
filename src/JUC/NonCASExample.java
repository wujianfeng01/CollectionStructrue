package JUC;

/**
 * 多线程非CAS操作，操作无法保证原子性
 */
public class NonCASExample {
    private int i = 0;
    public static void main(String[] args) {
        final NonCASExample example = new NonCASExample();
        // 创建多个线程并发地调用increment方法
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.i++;
                }
            }).start();
        }

        // 等待所有线程执行完毕
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出最终结果，不一定是5000
        System.out.println("Final counter value: " + example.i);
    }
}
