package sort;

/**
 * 选择排序
 * 初始状态下，所有元素未排序，即未排序（索引）区间为[0.n-1]。
 * 选取区间[0,n-1]中的最小元素，将其与索引 0 处元素交换。完成后，数组前 1 个元素已排序。
 * 选取区间[1,n-1]中的最小元素，将其与索引 1 处元素交换。完成后，数组前 2 个元素已排序。
 * 以此类推。经过 n-1 轮选择与交换后，数组前个元素已排序。
 * 仅剩的一个元素必定是最大元素，无需排序，因此数组排序完成。
 */
public class SelectSort {
    public static void doSort(int[] arr){
        // 外循环：未排序区间为 [i, n-1]
        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            // 内循环：找到未排序区间内的最小元素
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[min])min = j;
            }
            if (min!=i)Swap.swap(arr,i,min); // 交换元素
        }
    }
}
