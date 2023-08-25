package DataStructure.Iterator;

/**
 * 自定义迭代器接口
 */
public interface Iterator {
    /**
     * 判断是否有下一个元素
     * @return
     */
    public boolean hasNext();

    /**
     * 返回下一元素值
     * @return
     * @throws Exception
     */
    public int next() throws Exception;

    /**
     * 迭代器安全删除
     */
    public void remove() throws Exception;
}
