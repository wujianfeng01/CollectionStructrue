package sort;

/**
 * 1、输入数组并建立大顶堆。完成后，最大元素位于堆顶。
 * 2、将堆顶元素（第一个元素）与堆底元素（最后一个元素）交换。完成交换后，堆的长度减 1 ，已排序元素数量加 1。
 * 3、从堆顶元素开始，从顶到底执行堆化操作（adjustHeapDownByRecur）。完成堆化后，堆的性质得到修复。
 * 4、循环执行第 2 和 3 步。循环 n-1 轮后，即可完成数组排序。
 */
public class HeapSort {
    /**
     * 原地堆排序
     * @param arr
     */
    public static void doSort(int[] arr){
        buildHeap(arr);   // 建堆
        for (int i = arr.length - 1; i > 0; i--) {        // 排序
            Swap.swap(arr,0,i);               // 交换头尾元素
            adjustHeapDownByRecur(arr,0,i);   // 调整堆
        }
    }

    /**
     * 自底向上建堆操作
     * @param arr
     */
    private static void buildHeap(int[] arr){
        // 自底向上建堆
        for (int i = arr.length/2-1; i > -1; i--) {
            adjustHeapDownByRecur(arr,i, arr.length);
        }
    }

    /**
     * 调整堆下沉操作（递归）
     * @param arr
     * @param origin
     * @param len
     */
    private static void adjustHeapDownByRecur(int[] arr, int origin, int len){
        int left = 2*origin+1,right = 2*origin+2;   // 左子节点、右子节点
        int max = origin;                           // max变量指针
        if(left < len && arr[left] > arr[max])max = left;
        if(right < len && arr[right] > arr[max])max = right;

        if (max==origin)return;      // 不需要进行交换，直接返回
        Swap.swap(arr, max, origin); // 交换origin与max处的值
        adjustHeapDownByRecur(arr, max, len);   // 递归调整大顶堆
    }

    /**
     * 调整堆下沉操作（迭代）
     * @param arr
     * @param origin
     * @param len
     */
    private static void adjustHeapDownByWhile(int[] arr,int origin,int len){
        while (true){
            int left = 2*origin+1,right = 2*origin+2;   // 左子节点、右子节点
            int max = origin;                           // max变量指针
            if(left < len && arr[left] > arr[max])max = left;
            if(right < len && arr[right] > arr[max])max = right;

            if (max==origin)break;       // 不需要进行交换
            Swap.swap(arr, max, origin); // 交换origin与max处的值
            origin = max;
        }
    }
}
