package DataStructure.Array;

public class QueueBaseOnArray {
    private int[] base;
    private int front;
    private int size;
    public QueueBaseOnArray(int capacity){
        base = new int[capacity];
        front = 0;
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean isFull(){
        return size == capacity();
    }
    public int capacity(){
        return base.length;
    }
    public int length(){
        return size;
    }
    public void push(int val) {
        if (isFull()){
            throw new IndexOutOfBoundsException();
        }
        int tail = (front+size)%capacity();// 计算尾指针，指向队尾索引 + 1，通过取余操作，实现 tail 越过数组尾部后回到头部
        base[tail] = val;
        size++;
    }

    public int peek() {
        if(isEmpty())throw new NullPointerException();
        return base[front];
    }

    public int pop() {
        if(isEmpty())throw new IndexOutOfBoundsException();
        int num = base[front];
        front = (front+1)%capacity();  // 队首指针向后移动一位，若越过尾部则返回到数组头部
        size--;
        return num;
    }

    public int[] toArray(){
        int[] res = new int[size];
        int j = front;
        for (int i = 0; i < res.length; i++) {
            res[i] = base[j];
            j=(j+1)%capacity();
        }
        return res;
    }
}
