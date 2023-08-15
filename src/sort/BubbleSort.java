package sort;

/**
 * 冒泡排序
 * 首先，对 n 个元素执行“冒泡”，将数组的最大元素交换至正确位置，
 * 接下来，对剩余 n-1 个元素执行“冒泡”，将第二大元素交换至正确位置。
 * 以此类推，经过 n-1 轮“冒泡”后，前 n-1 大的元素都被交换至正确位置。
 * 仅剩的一个元素必定是最小元素，无需排序，因此数组排序完成
 */
public class BubbleSort {
    static void doSort(int[] arr){
        // 外循环，为排序区间为[i,arr.length-1]
        for (int i = 0; i < arr.length-1; i++) {
            boolean flag = false;     // 交换标志位
            // 内循环，将区间[i,arr.length-1]最小元素排序到区间最左端
            for (int j = arr.length-1; j > i ; j--) {
                if (arr[j]<arr[j-1]){
                    Swap.swap(arr,j,j-1);
                    flag = true;
                }
            }
            if (!flag)return;        // 未发生交换，说明元素有序，直接返回
        }
    }

}
