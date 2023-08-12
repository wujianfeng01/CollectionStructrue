package DataStructure.Array;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FailFastDemo {
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
        // 头删，会导致漏删元素
        for (int i = 0; i < lst.size(); i++) {
            if(lst.get(i) == i)lst.remove(i);
        }
        System.out.println(lst);

        // 集合ForEach语法糖，反编译实际使用Iterator进行遍历
        for (Integer i : lst) {
            int e = lst.get(i);
            System.out.println(i);
            if(e == 7)lst.remove(i);   // 使用集合函数删除，没有使用迭代器删除会出现modificationException
        }
    }

}
