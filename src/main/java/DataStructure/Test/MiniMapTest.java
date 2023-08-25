package DataStructure.Test;

import DataStructure.Map.MiniMap;

import java.util.concurrent.atomic.AtomicInteger;

public class MiniMapTest {
    public static void main(String[] args) throws InterruptedException {
        MiniMap map = new MiniMap();
        increment increment = new MiniMapTest().new increment();
        // 多线程写写互斥
        for (int i = 0; i < 10; i++) {
            increment.setI(i);
            int key = increment.getI();
            new Thread(()->{
                map.put("baidu"+key,key);
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(map.sum);
        System.out.println("===========");

        // 多线程读读兼容测试
        for (int i = 0; i < 10; i++) {
            increment.setI(i);
            int key = increment.getI();
            new Thread(()->{
                System.out.println(map.get("baidu"+key));
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("===========");

        // 多线程读写互斥测试
        new Thread(()->{            // 线程1.读取key为"baidu"的entry
            map.get("baidu0");

        }).start();

        new Thread(()->{            // 线程2.修改key为"baidu"的entry
            map.put("baidu0",1);
        }).start();
        System.out.println(map.get("baidu0"));
        Thread.sleep(1000);
        System.out.println(map.get("baidu0"));
    }

    /**
     * 内部类
     */
    public class increment{
        int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}
