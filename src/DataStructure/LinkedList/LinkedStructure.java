package DataStructure.LinkedList;

import DataStructure.Iterator.Iterator;

public class LinkedStructure {
    LinkedNodeStructure head;   // 头节点
    LinkedNodeStructure tail;   // 插入元素地最后一个节点
    int length;                 // 链表长度

    /**
     * 初始化
     */
    LinkedStructure(){
        head = new LinkedNodeStructure(Integer.MAX_VALUE);
        tail = head;
        length = 0;
    }

    /**
     * 尾插法
     * @param element
     */

    void insertTail(int element){
        LinkedNodeStructure nodeStructure = new LinkedNodeStructure(element);
        tail.next = nodeStructure;
        if(tail == head){
            tail.next = nodeStructure;
        }else{
            int tmp = nodeStructure.val;
            nodeStructure.val = tail.val;
            tail.val = tmp;
        }
        tail = nodeStructure;
        length++;
    }

    /**
     * 头插法插入元素
     * @param element
     */
    void insertHead(int element){
        LinkedNodeStructure nodeStructure = new LinkedNodeStructure(element);
        nodeStructure.next = head.next;
        head.next = nodeStructure;
        if(length == 0)tail = nodeStructure;
        length++;
    }

    /**
     * 根据index删除节点，双指针方式
     * @param index
     */
    void delete(int index){
        if(length == 0)return;
        if(index < 0)return;
        LinkedNodeStructure pre = head;
        LinkedNodeStructure cur = head.next;
        // 遍历寻找
        while(index > 0 && cur!=null ){
            pre = cur;
            cur = cur.next;
            index--;
        }
        pre.next = cur.next;
        if(cur == tail)tail = pre;
        length--;
    }

    /**
     * 寻找值为target的节点
     * @param target
     * @return
     */
    LinkedNodeStructure find(int target){
        LinkedNodeStructure cur = head.next;
        while (cur!=null){
            if (cur.val == target)return cur;
            cur = cur.next;
        }
        return null;
    }

    /**
     * 打印链表
     */
    void print(){
        LinkedNodeStructure lns = head.next;
        System.out.print("\n");
        while(lns!=null){
            System.out.print(lns.val+"--->");
            lns = lns.next;
        }
        System.out.print("null \n");
    }

    /**
     * 打印尾节点
     */
    void printTail(){
        if(tail == head)System.out.print("tail: head\n");
        else System.out.print("tail: "+tail.val+"\n");
    }

    /**
     * 打印头节点
     */
    void printHead(){
        System.out.println("head: "+head.val);
    }

    class LinkedStructureIterator implements Iterator{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public int next() throws Exception {
            return 0;
        }

        @Override
        public void remove() throws Exception {

        }
    }


}
