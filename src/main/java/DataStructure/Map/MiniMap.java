package DataStructure.Map;

import DataStructure.Iterator.Iterator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 精简的hashMap，key为String类型，value为int类型
 * 实现put、get、getKeys、printMap、containsKey、keyIsExistForPut、keyIsExistForDel等主要方法
 * 实现exCapability()哈希扩容机制
 * 红黑树转换方法bucketTransToRedBlackTree()待实现
 * 非线程安全（待实现）
 * 插入无序（带实现）
 */
public class MiniMap {
    private MiniEntry[] entryArr;        // 哈希桶数组
    public int limit;                   // 数组长度限制
    public int bucketLimit;             // 哈希桶长度限制
    public float expandValue;    // 哈希因子
    public int sum;              // 哈希元素个数
    public float expandLimit;    // 哈希因子限制
    private Lock[][] lock;          // 哈希桶读写锁
    private Lock mutex;             // 哈希桶全局锁
    public AtomicBoolean atomicBoolean;        // 扩容标志位

    /**
     * MiniMap初始化
     */
    public MiniMap(){
        entryArr = new MiniEntry[8];
        limit = 64;
        bucketLimit = 8;
        expandValue =0F;
        sum = 0;
        expandLimit = 0.75F;
        // 初始化哈希桶数组中的MiniEntry头节点
        for (int i = 0; i < entryArr.length; i++) {
            // false表示为链表，true表示为红黑树
            // value表示该哈希桶中的元素个数
            // hash值为初始化值
            entryArr[i] = new MiniEntry("false",0,0);
        }
        getLocks();                                   // 获取读写锁数组
        mutex = new ReentrantLock();                  // 全局锁
        atomicBoolean = new AtomicBoolean(false);
    }

    /**
     * 哈希函数
     * @param key
     * @return
     */
    // 哈希扰动函数
    public int hash(String key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 散列到的哈希桶id
     * @param hash
     * @return
     */
    public int buckets(int hash){
        return hash&(entryArr.length-1);
    }

    /**
     *
     * @param hash
     * @param len
     * @return
     */
    public int buckets(int hash, int len){
        return hash&(len-1);
    }

    /**
     * 插入entry<key,val>
     * @param key entry_key
     * @param val entry_value
     */
    public void put(String key, int val){
        int hash = hash(key);
        int bucketId = buckets(hash);
        if(atomicBoolean.get()){                                // 判断是否正在扩容
            mutex.lock();                                       // 获取全局锁
            lock[bucketId][1].lock();                           // 锁足哈希桶头节点
            try {
                putVal(bucketId,hash,key,val);
            }finally {
                lock[bucketId][1].unlock();                     // 释放锁
                mutex.unlock();                                 // 释放全局锁
            }
        }else {
            lock[bucketId][1].lock();                           // 写锁
            try {
                putVal(bucketId,hash,key,val);                  // 写操作
            }finally {
                lock[bucketId][1].unlock();                     // 释放写锁
                if(expandValue>expandLimit&&!atomicBoolean.get()) doCapacity(); // 根据扩容条件，判断是否扩容
            }
        }
    }

    /**
     * MiniMap添加元素实际方法
     * @param bucketId
     * @param hash
     * @param key
     * @param val
     */
    private void putVal(int bucketId,int hash,String key,int val){
        if(!keyIsExistForPut(bucketId,hash,key,val)) {  // entry不存在
            MiniEntry newEntry = new MiniEntry(key, val, hash);
            newEntry.setNext(entryArr[bucketId].getNext());
            entryArr[bucketId].setNext(newEntry);

            int value = entryArr[bucketId].getValue();    // 更新桶元素个数
            value++;
            entryArr[bucketId].setValue(value);

            sum++;                                        // 更新哈希元素个数
            expandValue = (float) sum / entryArr.length;  // 更新哈希因子
        }
    }

    /**
     * 根据key删除entry<key,value>
     * @param key
     * @return 删除是否成功
     */
    public boolean delete(String key){
        int hash = hash(key);
        int bucketId = buckets(hash);
        return keyIsExistForDel(bucketId, hash, key);
    }

    /**
     * 根据key值获取value值
     * @param key
     * @return
     */
    public int get(String key){
        int hash = hash(key);
        int bucketId = buckets(hash);
        lock[bucketId][0].lock();           // 加读锁
        try {
            MiniEntry cur = entryArr[bucketId].getNext();
            while(cur!=null){
                if (cur.getKey().equals(key))return cur.getValue();
                cur = cur.getNext();
            }
            return Integer.MIN_VALUE;
        } finally {
            lock[bucketId][0].unlock();       // 释放读锁
        }

    }

    /**
     * put方法检查key是否存在
     * @param bucketId
     * @param hash
     * @param key
     * @param val
     * @return  key是存在
     */
    protected boolean keyIsExistForPut(int bucketId, int hash, String key, int val){
        MiniEntry cur = entryArr[bucketId].getNext();
        while(cur!=null){
            if(cur.getHash() ==hash){             // 判断hash值是否相等
                if (cur.getKey().equals(key)){    // hash值相等的情况下，判断key值是否相等
                    cur.setValue(val);
                    return true;
                }
            }
            cur = cur.getNext();

        }
        return false;
    }

    /**
     * delete方法检测key是否存在
     * @param bucketId
     * @param hash
     * @param key
     * @return
     */
    private boolean keyIsExistForDel(int bucketId,int hash,String key){
        MiniEntry pre = entryArr[bucketId];
        MiniEntry cur = entryArr[bucketId].getNext();
        while(cur!=null){
            if(cur.getHash() ==hash){
                if (cur.getKey().equals(key)){
                    pre.setNext(cur.getNext());
                    cur.setNext(null);
                    int val = entryArr[bucketId].getValue();
                    val--;
                    entryArr[bucketId].setValue(val);
                    sum--;
                    expandValue =(float)sum/entryArr.length;
                    return true;
                }
            }
            pre = cur;
            cur = cur.getNext();
        }
        return false;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean containsKey(String key){
        int hash = hash(key);
        int bucket = buckets(hash);
        MiniEntry cur = entryArr[bucket].getNext();
        while(cur!=null){
            if(cur.getHash() ==hash){
                if (cur.getKey().equals(key)){
                    return true;
                }
            }
            cur = cur.getNext();

        }
        return false;
    }

    /**
     * 获取键数组
     * @return
     */
    public String[] getKeys(){
        String[] keys = new String[sum];
        int i = 0;
        for(MiniEntry entry:entryArr){
            if(entry.getValue() >0){
                MiniEntry cur = entry.getNext();
                while(cur!=null&&i<keys.length){
                    keys[i++] = cur.getKey();
                    cur = cur.getNext();
                }
            }
        }
        return keys;
    }

    /**
     * 打印MiniMap数组
     */
    public void printArrMap(){
        System.out.print("[ ");
        for(MiniEntry mini:entryArr){
            System.out.print(mini.getKey() +"->"+ mini.getValue() +" ");
        }
        System.out.print("]\n");
    }

    /**
     * 打印指定哈希桶桶内元素
     * @param id
     */
    public void printBucket(int id){
        MiniEntry entry = entryArr[id].getNext();
        while(entry!=null){
            System.out.println(entry.getKey() +"->"+ entry.getValue());
            entry = entry.getNext();
        }
    }

    /**
     * 打印所有哈希桶元素
     */
    public void printMap(){
        for(MiniEntry entry:entryArr){
            if(entry.getValue() >0){
                MiniEntry cur = entry.getNext();
                while(cur!=null){
                    System.out.println("{ "+ cur.getKey() +"->"+ cur.getValue() +" }");
                    cur = cur.getNext();
                }
            }
        }
    }

    /**
     * 哈希扩容机制
     * @return
     */
    private void doCapacity(){
        if(entryArr.length<limit) {   // 未超过数组长度限制
            mutex.lock();             // 获取全局锁
            try {
                CAS();                // 更改扩容标志位
                capacity();           // 扩容方法
                unCAS();              // 更改扩容标志位
            }finally {
                mutex.unlock();       // 释放全局锁
            }
        }else{
            System.out.println("数组长度到达上线......");
            for (int i = 0; i < entryArr.length; i++) {
                if(entryArr[i].getValue()>=8){      // 哈希桶长度超过限制，进行红黑树转换
                    bucketTransToRedBlackTree(i);
                }
            }
        }

    }

    /**
     * 实际数组扩容方法
     */
    private void capacity(){
        int newLen = entryArr.length*2;
        MiniEntry[] res = new MiniEntry[newLen];
        for (int i = 0; i < res.length; i++) {                  // 初始化哈希桶数组
            res[i] = new MiniEntry("false",0,0);
        }
        int newSum = 0;
        for (int i = 0; i < entryArr.length; i++) {
            if(entryArr[i].getValue()==0)continue;
            MiniEntry cur = entryArr[i].getNext();               // 获取哈希桶头节点下一个元素
            while (cur!=null){  //遍历旧哈希桶
                // 删除旧哈希桶中的entry
                MiniEntry next = cur.getNext();
                delete(cur.getKey());

                int bucketId = buckets(cur.getHash(),newLen);    // rehash操作

                cur.setNext(res[bucketId].getNext());            // 旧哈希桶通过头插法插入新哈希桶
                res[bucketId].setNext(cur);
                newSum++;                                        // 记录新哈希元素个数

                int val = res[bucketId].getValue();              // 记录新哈希桶元素个数
                res[bucketId].setValue(++val);

                cur = next;                                      // cur=cur.next;
            }
        }
        entryArr = res;
        sum = newSum;                               // 更新哈希元素个数
        getLocks();                                 // 更新锁数组
        expandValue = (float) sum/entryArr.length;  // 更新哈希因子
    }

    /**
     * 原子性将扩容状态设置为true
     */
    private void CAS() {
        int count = 5;  // CAS次数上限
        do {
            count--;
        } while (count>0&&!atomicBoolean.compareAndSet(false, true));
    }

    /**
     * 原子性将扩容状态设置为true
     */

    private void unCAS() {
        int count = 5;   // unCAS次数上限
        do {
            count--;
        } while (count>0&&!atomicBoolean.compareAndSet(true, false));
    }
    /**
     * 获取锁数组
     */
    private void getLocks(){
        lock = new Lock[entryArr.length][2];
        for (int i = 0; i < lock.length; i++) {
            ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            lock[i][0] = readWriteLock.readLock();    // 读锁
            lock[i][1] = readWriteLock.writeLock();   // 写锁
        }
    }


    /**
     * 链表转红黑树（未实现）
     * @param bucketId
     */
    private void bucketTransToRedBlackTree(int bucketId) {
        if (entryArr[bucketId].getKey().equals("false")) {
            System.out.println("开始将哈希桶[" + bucketId + "]链表转换成红黑树....");
            entryArr[bucketId].setKey("true");
        } else {
            System.out.println("哈希桶[" + bucketId + "]已经转换成红黑树，直接插入红黑树");
        }
    }

    /**
     * Map迭代器内部类
     * 待实现
     */
    class MapIterator implements Iterator{

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
