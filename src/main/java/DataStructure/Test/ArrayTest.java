package DataStructure.Test;

import DataStructure.Array.ArrayStructure;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ArrayStructure结构测试类
 */
public class ArrayTest {
    public static void main(String[] args) throws Exception {
        ArrayStructure structure = new ArrayStructure();
        // 并发修改list测试
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    try {
                        structure.insertTail(j); // 非线程安全方法
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
        Thread.sleep(5);
        System.out.println(structure.size());
        ArrayStructure.ListIterator iteration = structure.getIteration();


    }
}
