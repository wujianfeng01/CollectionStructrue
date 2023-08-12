package DataStructure.Array;

import DataStructure.Iterator.Iterator;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数组封装类
 * 数组的插入、删除、扩容操作
 * 实现迭代器地基本操作
 * 实现线程安全
 */
public class ArrayStructure{
    private int[] arr;         // 存储容器
    private int pos;           // 存储数据的实际长度
    private int array_count;   // ArrayStructure的修改次数
    private Lock lock;         // lock

    /**
     * 初始化
     */
    public ArrayStructure(){
        arr = new int[5];
        Arrays.fill(arr,Integer.MIN_VALUE);
        pos = 0;
        array_count = 0;
        lock = new ReentrantLock();
    }

    /**
     * 尾插法，并发安全
     * @param element
     * @throws Exception
     */
    public void insertTailForSafe(int element) throws Exception {
        insertByIndexForSafe(element,pos);
    }

    /**
     * 数组尾插，非并发安全
     * @param element
     */
    public void insertTail(int element) throws Exception {
        insertByIndex(element,pos);
    }

    /**
     * 插入元素到index位置，并发安全
     * @param element
     * @param index
     * @throws Exception
     */
    public void insertByIndexForSafe(int element,int index) throws Exception {
        lock.lock();
        try {
            // 插入位置大于实际存储长度或者插入位置小于0，抛出异常
            if(index>pos||index<0)throw new Exception("index is "+index+",ArrayStructure length is "+pos);
            if(pos==arr.length)reCapacity(pos);
            for (int i = pos; i > index ; i--) {
                arr[i] = arr[i-1];
            }
            arr[index] = element;
            pos++;
            array_count++;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 数组指定位置插入元素，非并发安全
     * @param element
     * @param index
     * @return
     * @throws Exception
     */
    public void insertByIndex(int element,int index) throws Exception {
        // 插入位置大于实际存储长度或者插入位置小于0，抛出异常
        if(index>pos||index<0)throw new Exception("index is "+index+",ArrayStructure length is "+pos);
        if(pos==arr.length)reCapacity(pos);
        for (int i = pos; i > index ; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = element;
        pos++;
        array_count++;
    }

    /**
     * 数组指定位置删除，并发安全
     * @param index
     * @throws Exception
     */
   public void deleteByIndexForSafe(int index) throws Exception {
       lock.lock();
       try {
           // 插入位置大于等于实际存储长度或者删除位置小于0，抛出异常
           if(index >= pos||index < 0)throw new Exception("index is "+index+",ArrayStructure length is "+pos);
           for (int i = index; i < pos-1; i++) {
               arr[i] = arr[i+1];
           }
           arr[--pos] = Integer.MIN_VALUE;
           array_count++;
       }finally {
           lock.unlock();
       }

    }

    /**
     * 数组指定位置删除，非并发安全
     * @param index
     * @throws Exception
     */
    public void deleteByIndex(int index) throws Exception {
        // 插入位置大于等于实际存储长度或者删除位置小于0，抛出异常
        if(index >= pos||index < 0)throw new Exception("index is "+index+",ArrayStructure length is "+pos);
        for (int i = index; i < pos-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[--pos] = Integer.MIN_VALUE;
        array_count++;
    }

    /**
     * 数组尾删除，并发安全
     * @throws Exception
     */
    public void deleteTailForSafe() throws Exception {
        deleteByIndexForSafe(pos-1);
    }

    /**
     * 数组尾删
     * @throws Exception
     */
    public void deleteTail() throws Exception {
        deleteByIndex(pos-1);
    }

    /**
     * 获取指定index的元素
     * @param index
     * @return
     * @throws Exception
     */
    public int get(int index) throws Exception {
        if(index<pos&&index>-1)return arr[index];
        // 获取元素下标大于等于实际存储长度或者小于0，抛出异常
        else throw new Exception("index is "+index+",ArrayStructure length is "+pos);
    }

    /**
     * 返回ArrayStructure数据的实际长度
     * @return
     */
    public int size(){
        return pos;
    }

    /**
     * 打印实际数据
     */
    public void print(){
        System.out.print("[ ");
        for (int i = 0; i < pos; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.print("]\n");
    }

    /**
     * 数组扩容
     * @param addition
     */
    void reCapacity(int addition){
        int[] res = new int[arr.length+addition];
        Arrays.fill(res,Integer.MIN_VALUE);
        for (int i = 0; i < pos; i++) {
            res[i] = arr[i];
        }
        arr = res;
    }

    /**
     * 获取迭代器
     * @return
     */
    public ListIterator getIteration(){
        return new ListIterator();
    }

    /**
     * list迭代器内部类
     */
    public class ListIterator implements Iterator {
        int iterator_count;  // 修改次数
        int ptr;   // 迭代器指针变量
        ListIterator(){      // 初始化
            iterator_count = array_count;
            ptr = -1;
        }

        /**
         * 判断是否有下一个元素
         * @return
         */
        @Override
        public boolean hasNext() {
            if((ptr+1)<pos)return true;
            return false;
        }

        /**
         * 返回下一个元素
         * @return
         * @throws Exception
         */
        @Override
        public int next() throws Exception {
            if(checkCount(array_count)){
                lock.lock();
                try {
                    return arr[++ptr];
                }finally {
                    lock.unlock();
                }
            }else {
                throw new Exception("Iterator And ArrayStructure's count is notConsistent");
            }
        }

        /**
         * 迭代器删除
         * 当前迭代器删除实现安全删除，使用游标ptr更新的方式解决该问题
         * @throws Exception
         */
        public void remove() throws Exception {
            lock.lock();
            try {
                if (ptr >= pos)return;
                for (int i = ptr; i < pos-1; i++) {    // 移动元素
                    arr[i] = arr[i+1];
                }
                pos--;            // 更新数组实际长度
                ptr--;            // 更新迭代器游标位置
                array_count++;    // 更新ArrayStructure修改次数
                iterator_count++; // 更新迭代器修改次数
            }finally {
                lock.unlock();
            }


        }

        /**
         * 检查迭代器和容器修改次数是否一致
         * @param listCount
         * @return
         */
        public boolean checkCount(int listCount){
            return iterator_count==listCount;
        }
    }
}
