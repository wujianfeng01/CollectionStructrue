package DataStructure.LinkedList;

public class LinkStructureTest {
    public static void main(String[] args) {
        LinkedStructure ls = new LinkedStructure();
        // 头插测试
        ls.insertHead(0);
        ls.insertHead(1);
        ls.insertHead(2);

        // 链表遍历
        ls.print();
        // 打印头节点
        ls.printHead();
        // 打印尾节点
        ls.printTail();

        // 删除测试
        ls.delete(2);
        ls.print();
        ls.printHead();
        ls.printTail();

        // 尾插法测试
        ls.insertTail(0);
        ls.print();
        ls.printHead();
        ls.printTail();

        // 测试find方法
        LinkedNodeStructure nodeStructure = ls.find(2);
        System.out.println(nodeStructure.val);
    }
}
