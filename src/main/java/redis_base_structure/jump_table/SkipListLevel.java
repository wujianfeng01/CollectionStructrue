package redis_base_structure.jump_table;

/**
 * 跳表节点层级
 */
public class SkipListLevel {
    SkipListNode forward;    //   前向指针
    long span;               //   步长
    public SkipListLevel(){}
    public SkipListLevel(SkipListNode node,long span){
        forward = node;
        this.span = span;
    }
}
