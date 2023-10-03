package redis_base_structure.jump_table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 跳表节点
 */
public class SkipListNode {
    String ele;                    // 跳表节点的元素值
    double score;                  // 跳表节点权重
    SkipListNode backward;         // 跳表节点的后置指针
    final static int MAX_LEVEL = 64;      //  节点层数上限
    SkipListLevel[]  levels = new SkipListLevel[MAX_LEVEL];   // 跳表节点的层级列表
    Double isProbability = 0.125;        // 节点升级概率阈值
    int level = 1;                       // 初始化当前节点层级

    /**
     * 构造器初始化
     * @param ele
     * @param score
     * @param backward
     */
    public SkipListNode(String ele,Double score,SkipListNode backward){
        this.ele = ele;
        this.score = score;
        this.backward = backward;
        createLevels();
    }

    /**
     * 默认构造器
     */
    public SkipListNode(){
        createLevels();
    }

    /**
     * 创建节点层级
     */
    private void createLevels(){
        int levels = getProbability(this.level);
        for (int i = 2; i < levels + 1; i++) {
            this.levels[i] = new SkipListLevel();
        }
        this.level = levels;
        System.out.println(this.level);
    }

    /**
     * 返回节点生成层级（递归）
     * @param level
     * @return
     */
    private int getProbability(int level){
        Double num =Math.random();  // [0,1)随机数
        if (level <= MAX_LEVEL && num <= this.isProbability){
            level++;
            return getProbability(level);
        }
        return level;
    }
}
