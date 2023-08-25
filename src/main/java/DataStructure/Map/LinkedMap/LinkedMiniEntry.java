package DataStructure.Map.LinkedMap;

import DataStructure.Map.MiniEntry;

/**
 * LinkedMiniMap哈希桶元素类
 * 包含MiniMap哈希桶元素类MiniEntry对象和LinkedMiniEntry类型的left、right变量指针
 */
public class LinkedMiniEntry{
    LinkedMiniEntry left;
    LinkedMiniEntry right;
    MiniEntry entry;
    public LinkedMiniEntry(){}
    public LinkedMiniEntry(String key,int val,int hash){
        entry = new MiniEntry(key,val,hash);
    }

    public LinkedMiniEntry getLeft() {
        return left;
    }

    public void setLeft(LinkedMiniEntry left) {
        this.left = left;
    }

    public LinkedMiniEntry getRight() {
        return right;
    }

    public void setRight(LinkedMiniEntry right) {
        this.right = right;
    }

    public MiniEntry getEntry() {
        return entry;
    }

    public void setEntry(MiniEntry entry) {
        this.entry = entry;
    }
}
