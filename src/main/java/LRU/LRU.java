package LRU;

import java.util.LinkedList;

/**
 * LRU简单实现
 * @param <T>
 */
public class LRU<T> {
    LinkedList<T> linkedList;  // 底层数据结构双链表
    int limit;                 // 内存限制值
    public LRU(int limit){
        this.limit = limit;
        linkedList = new LinkedList<>();
    }

    /**
     * 获取LRU中的元素
     * @param t
     * @return
     */
    public T get(T t) {
        int exist = nodeIsExist(t);
        if (exist>-1){
            T res = linkedList.get(exist);
            deleteByIndex(exist);
            addFirst(t);
            return res;
        }
        return null;
    }

    /**
     * 更新LRU中的元素
     * @param t
     */
    public void put(T t){
        int exist = nodeIsExist(t);
        if(exist>-1)deleteByIndex(exist);
        addFirst(t);
        if (linkedList.size()>limit)delete();
    }

    /**
     * 判断元素是否存在
     * 存在返回元素下标
     * 不存在返回-1
     * @param t
     * @return
     */
    private int nodeIsExist(T t){
        if(linkedList.size()==0)return -1;
        int count = -1;
        for (T ele : linkedList) {
            count++;
            if(t == ele)return count;
        }
        return -1;
    }

    /**
     * 删除尾元素
     */
    private void delete(){
        linkedList.removeLast();
    }

    /**
     * 根据下标删除元素
     * @param index
     */
    private void deleteByIndex(int index){
        linkedList.remove(index);
    }

    /**
     * 头插法加入元素
     * @param t
     */
    private void addFirst(T t){
        linkedList.addFirst(t);
    }


}
