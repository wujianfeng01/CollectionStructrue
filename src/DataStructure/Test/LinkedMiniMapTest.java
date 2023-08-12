package DataStructure.Test;

import DataStructure.Map.LinkedMap.LinkedMiniEntry;
import DataStructure.Map.LinkedMap.LinkedMiniMap;

/**
 * 测试LinkedMiniMap类
 */
public class LinkedMiniMapTest {
    public static void main(String[] args) {
        LinkedMiniMap linkedMiniMap = new LinkedMiniMap();
        System.out.println("map元素总个数为："+linkedMiniMap.sum);
        System.out.println("map哈希因子为："+linkedMiniMap.expandValue);
        System.out.println("====================");

        // 添加测试
        linkedMiniMap.put("baidu",1);
        linkedMiniMap.put("tencent",4);
        linkedMiniMap.put("red book",9);
        linkedMiniMap.put("bytedance",16);
        linkedMiniMap.put("xiaomi",25);
        linkedMiniMap.printArrMap();
        linkedMiniMap.printMap();
        linkedMiniMap.printOrderMap();
        System.out.println("map元素总个数为："+linkedMiniMap.sum);
        System.out.println("map哈希因子为："+linkedMiniMap.expandValue);
        System.out.println("====================");
        // 删除测试
        linkedMiniMap.delete("bytedance");
        linkedMiniMap.delete("baidu");
        linkedMiniMap.printMap();
        linkedMiniMap.printOrderMap();
        System.out.println("map元素总个数为："+linkedMiniMap.sum);
        System.out.println("map哈希因子为："+linkedMiniMap.expandValue);
        System.out.println("====================");
        // get测试
        System.out.println(linkedMiniMap.get(("red book")));
        System.out.println(linkedMiniMap.get(("blue book")));


    }



}
