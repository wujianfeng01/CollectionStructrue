package DataStructure.Test;

import DataStructure.Array.QueueBaseOnArray;

public class QueueTest {
    public static void main(String[] args) throws Exception {
        QueueBaseOnArray queueBaseOnArray = new QueueBaseOnArray(5);
        // 入队测试
        for (int i = 0; i < queueBaseOnArray.capacity(); i++) {
            queueBaseOnArray.push(i);
        }
        System.out.println(queueBaseOnArray.length());

        // 入队异常测试
        try {
            queueBaseOnArray.push(5);
        }catch (Exception ex){
            System.out.println(ex);
        }

        // 转数组
        int[] array = queueBaseOnArray.toArray();
        for (int num :
                array) {
            System.out.print(num+" ");   // 打印数组元素
        }
        System.out.println();
        System.out.println(queueBaseOnArray.length());

        // 出队测试
        int size = queueBaseOnArray.length();
        for (int i = 0; i < size; i++) {
            System.out.print(queueBaseOnArray.pop()+" ");
        }
        System.out.println();
        System.out.println(queueBaseOnArray.length());

        // 出队异常测试
        try {
            queueBaseOnArray.pop();
            queueBaseOnArray.peek();
        }catch (IndexOutOfBoundsException|NullPointerException ex){
            System.out.println(ex);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
