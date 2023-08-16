package sort;

/**
 * 快速排序
 * 1、选取数组最左端元素作为基准数，初始化两个指针 i 和 j 分别指向数组的两端。
 * 2、设置一个循环，在每轮中使用 i（j）分别寻找第一个比基准数大（小）的元素，然后交换这两个元素。
 * 3、循环执行步骤 2，直到 i 和 j 相遇时停止，最后将基准数交换至两个子数组的分界线。
 * 完成后，原数组被划分成三部分：左子数组、基准数、右子数组
 * 且满足“左子数组任意元素 <= 基准数 <= 右子数组任意元素”。
 *
 * 因此，接下来只需递归对这两个子数组进行排序。
 */
public class QuickSort {
    /**
     * 执行快排
     * @param arr
     */
    public static void doSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序操作
     * @param arr 待排序数组区间[i,j]
     * @param i   待排序左边界
     * @param j   待排序右边界
     */
    private static void quickSort(int[] arr, int i, int j){
        if (i >= j)return;   // 中止条件
        int plot = i;        // 记录基准元素位置
        int right = j;       // 记录右边界
        while(i < j){        // 找到哨兵位置i==j
            while (i < j && arr[j] >= arr[plot])j--;
            while (i < j && arr[i] <= arr[plot])i++;
            Swap.swap(arr,i,j);
        }
        Swap.swap(arr,plot,i);   // 交换基准元素与哨兵位置元素
        quickSort(arr,plot,i-1);     // 递归遍历左数组
        quickSort(arr,i+1,right);    // 递归遍历右数组
    }
}
