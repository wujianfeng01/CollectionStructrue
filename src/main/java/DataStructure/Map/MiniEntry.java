package DataStructure.Map;

/**
 * MiniMap哈希桶元素类
 */
public class MiniEntry {
    private String key;
    private int value,hash;
    private MiniEntry next;
    // 默认构造器
    public MiniEntry(){

    }
    // 自定义构造器
    public MiniEntry(String key, int value, int hash){
        this.key = key;
        this.value = value;
        this.hash = hash;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public MiniEntry getNext() {
        return next;
    }

    public void setNext(MiniEntry next) {
        this.next = next;
    }
}
