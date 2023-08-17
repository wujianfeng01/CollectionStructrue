package sort;

import java.util.*;

/**
 * 1、初始化 k 个桶，将 n 个元素分配到 k 个桶中。
 * 2、对每个桶分别执行排序
 * 3、按照桶的从小到大的顺序，合并结果。
 */
public class BucketSort {
    static ArrayList<Integer>[] bucketList;
    /**
     * 桶排序
     * @param arr
     */
    public static void doSort(int[] arr){
        sortBuckets(arr);
        int cur = 0;
        for (List<Integer> list : bucketList) {        // 遍历桶合并结果
            if (list != null && list.size() > 0) {
                for (int i : list) {
                    arr[cur++] = i;
                }
            }
        }
    }

    /**
     * 排序各个桶
     * @param arr
     */
    private static void sortBuckets(int[] arr){
        int k = getMean(arr);     // 获取平均数
        mappingToBucket(arr,k);   // 将待排序数组元素映射到各个桶中
        for (List<Integer> list : bucketList) {  //  给每个桶进行排序
            // 过滤null和只有一个元素的桶
            if (list != null && list.size() > 1) Collections.sort(list);
        }
    }

    /**
     * 元素值 [ arr[min],arr[max] ] 压缩到 [0,1], 映射到桶 [0,k - 1]
     * @param arr
     */
    private static void mappingToBucket(int[] arr,int k){
        bucketNums(k);
        int max = findMax(arr);
        for (int i : arr) {
            float mappingNum = (float)i / max;              // [0,1]
            int mappingId = (int) (mappingNum * (bucketList.length - 1));      // [0,k - 1]
            // 桶有元素放入才创建ArrayList
            if (bucketList[mappingId] == null)bucketList[mappingId] = new ArrayList();
            bucketList[mappingId].add(i);
        }
    }

    /**
     * 设置桶个数
     * @param k
     */
    private static void bucketNums(int k){
        bucketList = new ArrayList[k];
    }

    /**
     * 查找待排序最大元素
     * @param arr
     * @return
     */
    private static int findMax(int[] arr){
        int max = arr.length - 1;
        int i = 0;
        while (i < arr.length - 1){
            if (arr[i] > arr[max])max = i;
            i++;
        }
        return arr[max];
    }

    /**
     * 获取待排序数组平均值
     * @param arr
     * @return
     */
    private static int getMean(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
        }
        return sum/arr.length;
    }

}
