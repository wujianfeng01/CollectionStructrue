package DataStructure.Map.LinkedMap;

import DataStructure.Map.MiniEntry;
import DataStructure.Map.MiniMap;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有序的MiniMap类
 * 实现了put、delete、get、printOrderMap、printPostOrderMap等方法
 * 未实现并发安全机制
 * 未实现扩容机制
 * 未实现红黑树机制
 */
public class LinkedMiniMap extends MiniMap {
    LinkedMiniEntry[] linkedMiniEntries;  // 哈希桶元素类型
    LinkedMiniEntry head;                 // 头节点
    LinkedMiniEntry tail;                 // 尾节点
    Lock[] locks;                         // node锁

    /**
     * 初始化操作
     * 头节点、尾节点、哈希桶个数、锁数组
     */
    public LinkedMiniMap(){
        head = new LinkedMiniEntry("head",0,0);
        tail = new LinkedMiniEntry("tail",-1,-1);
        head.left = null;
        head.right = tail;
        tail.left = head;
        tail.right = null;
        linkedMiniEntries = new LinkedMiniEntry[8];
        locks = new ReentrantLock[linkedMiniEntries.length];
        // 初始化LinkedMiniMap数组
        for (int i = 0; i < linkedMiniEntries.length; i++) {
            linkedMiniEntries[i] = new LinkedMiniEntry("bucketHead["+i+"]",0,0);
        }
        // 初始化锁数组
        Arrays.fill(locks,new ReentrantLock());

    }

    /**
     * 添加LinkedMiniEntry元素
     * @param key entry_key
     * @param val entry_value
     */
    @Override
    public void put(String key, int val) {
        int hash = this.hash(key);
        int bucket = this.buckets(hash);
        if(!keyIsExistForPut(bucket,hash,key,val)){
            // 创建哈希桶元素
            LinkedMiniEntry linkedMiniEntry = new LinkedMiniEntry(key,val,hash);
            // 设置MiniEntry元素next变量
            linkedMiniEntry.getEntry().setNext(linkedMiniEntries[bucket].getEntry().getNext());
            linkedMiniEntries[bucket].getEntry().setNext(linkedMiniEntry.getEntry());
            // 设置linkedMiniEntry的left、right变量（头插法）
            linkedMiniEntry.right = head.right;
            head.right = linkedMiniEntry;
            linkedMiniEntry.left = head;
            linkedMiniEntry.right.left = linkedMiniEntry;
            // 更新当前哈希桶元素个数
            int value = linkedMiniEntries[bucket].getEntry().getValue();
            value++;
            linkedMiniEntries[bucket].getEntry().setValue(value);
            // 更新当前哈希因子
            this.expandValue = (float) this.sum/linkedMiniEntries.length;
            // 更新整个map的元素个数
            this.sum++;
        }
    }

    /**
     * 删除键为key的LinkedMiniEntry
     * @param key
     * @return
     */
    @Override
    public boolean delete(String key) {
        int hash = hash(key);
        int bucket = buckets(hash);
        MiniEntry pre = linkedMiniEntries[bucket].getEntry();
        MiniEntry cur  = linkedMiniEntries[bucket].getEntry().getNext();
        while(cur!=null){
            if(cur.getHash()==hash){
                if(cur.getKey().equals(key)){
                    pre.setNext(cur.getNext());
                    cur.setNext(null);
                    break;
                }
            }
            pre = cur;
            cur = cur.getNext();
        }
        LinkedMiniEntry ptr = head;
        while(ptr!=tail){
            if(ptr.getEntry().getHash()==hash){
                if(ptr.getEntry().getKey().equals(key)){
                    // 删除双链表元素
                    ptr.left.right = ptr.right;
                    ptr.right.left = ptr.left;
                    ptr.right = null;
                    ptr.left = null;
                    // 更新当前哈希桶元素个数
                    int value = linkedMiniEntries[bucket].getEntry().getValue();
                    linkedMiniEntries[bucket].getEntry().setValue(--value);
                    // 更新整个map元素个数
                    this.sum--;
                    // 更新哈希因子
                    this.expandValue = (float) sum/linkedMiniEntries.length;
                    return true;
                }
            }
            ptr = ptr.right;
        }
        return false;
    }

    /**
     * 获取Map的value值
     * @param key
     * @return
     */
    @Override
    public int get(String key) {
        int hash = this.hash(key);
        int bucketId = buckets(hash);
        MiniEntry cur = linkedMiniEntries[bucketId].getEntry().getNext();
        while(cur!=null){
            if(cur.getHash()==hash){
                if(cur.getKey().equals(key))return cur.getValue();
            }
            cur = cur.getNext();
        }
        return Integer.MIN_VALUE;
    }

    /**
     * 插入顺序的倒序遍历
     */
    public void printPostOrderMap() {
        LinkedMiniEntry cur = head.right;
        while(cur!=tail){
            System.out.println(cur.getEntry().getKey()+"->"+cur.getEntry().getValue());
            cur = cur.right;
        }
    }

    /**
     * 插入顺序的顺序遍历
     */
    public void printOrderMap() {
        LinkedMiniEntry cur = tail.left;
        while(cur!=head){
            System.out.print(cur.getEntry().getKey()+"->"+cur.getEntry().getValue()+" ");
            cur = cur.left;
        }
        System.out.print("\n");
    }

    /**
     * 默认的Map遍历方式，与插入顺序无关
     */
    @Override
    public void printMap() {
        for (LinkedMiniEntry linkedMiniEntry
                : linkedMiniEntries
             ) {
            if(linkedMiniEntry.getEntry().getValue()>0){
                MiniEntry cur = linkedMiniEntry.getEntry().getNext();
                while(cur!=null){
                    System.out.print(cur.getKey()+"->"+cur.getValue()+" ");
                    cur = cur.getNext();
                }
            }
        }
        System.out.print("\n");
    }

    /**
     * 默认打印Map的方式
     */
    public void printArrMap(){
        for (LinkedMiniEntry linkedEntry :
                linkedMiniEntries) {
            System.out.print(linkedEntry.getEntry().getKey()+"->"+linkedEntry.getEntry().getValue()+" ");
        }
        System.out.print("\n");
    }
}
