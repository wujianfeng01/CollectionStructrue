package redis_base_structure.jump_table;

/**
 * 跳表
 */
public class SkipList {
    SkipListNode head,tail;   // 头尾指针
    long size;       // 跳表长度
    int level;       // 跳表当前实际最大层数

    /**
     * 初始化跳表成员变量
     * 初始化头节点、尾节点
     */
    private SkipList(){
        this.size = 0;
        this.level = 1;
        this.head = new SkipListNode();
        this.tail = new SkipListNode("tail",Double.MAX_VALUE,head);
        head.backward = null;
        tail.backward = head;
        for (int i = 0; i < SkipListNode.MAX_LEVEL ; i++) {
            head.levels[i] = new SkipListLevel();
            tail.levels[i] = new SkipListLevel();
            head.levels[i].forward = tail;
            tail.levels[i].forward= null;
        }
        head.level = SkipListNode.MAX_LEVEL;
        tail.level = SkipListNode.MAX_LEVEL;
    }

    /**
     * 暴露给外部创建跳表的接口函数
     * @return
     */
    public static SkipList creatSL(){
       return new SkipList();
    }

    /**
     * 插入节点
     * @param sds
     * @param score
     * @return
     */
    public boolean offer(String sds,double score){
        return false;
    }

    /**
     * 删除节点
     * @param sds
     * @param score
     * @return
     */
    public boolean poll(String sds,double score){
        return false;
    }

    /**
     * 查询
     * @param sds
     * @param score
     * @return
     */
    public SkipListNode selectByKV(String sds,double score){
        return null;
    }

    /**
     * 更新跳表最大层级
     * @param level
     */
    private void updateListLevel(int level){
        if (level > this.level)this.level = level;
    }

}
