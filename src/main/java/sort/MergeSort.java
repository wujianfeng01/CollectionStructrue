package sort;

import java.util.Arrays;

/**
 * 归并排序，基于分治的递归实现
 * 1、“分阶段”自顶向下递归地将数组从中点切为两个子数组计算数组中点 mid ，递归划分左子数组（区间 [left, mid] ）和右子数组（区间 [mid + 1, right] ）。
 * 2、递归执行步骤 1 ，直至子数组区间长度为 1 时，终止递归划分。
 * 3、“合并阶段”自底向上地将左子数组和右子数组合并为一个有序数组。
 * 需要注意的是：！从长度为 1 的子数组开始合并 ！合并阶段中的每个子数组都是有序的。
 */
public class MergeSort {
    public static void doSort(int[] arr){
        mergeSort(arr,0,arr.length-1);
    }

    /**
     * 分操作
     * @param arr
     * @param i
     * @param j
     */
    private static void mergeSort(int[] arr, int i, int j){
        if (i==j)return;      // 数组区间长度为 1 时，终止递归划分
        int mid = (i+j)/2;
        mergeSort(arr,i,mid);          // 左数组[i,mid]
        mergeSort(arr,mid+1,j);     // 右数组[mid+1,j]
        merge(arr,i,j,mid);           // 治（左右数组已经有序）
    }

    /**
     * 治操作
     * @param arr
     * @param left
     * @param right
     * @param mid
     */
    private static void merge(int[] arr, int left, int right, int mid){
        if(left == right)return;                                        // 一个元素，直接返回
        int[] tmp = Arrays.copyOfRange(arr, left, right + 1);        // 深度复制待排序素组
        int leftStart = left - left,leftEnd = mid - left;               // 左数组区间[leftStart,leftEnd]
        int rightStart = mid - left + 1,rightEnd = right - left;        // 右数组区间[rightStart,rightEnd]
        int i = leftStart,j = rightStart;                               // 子数组遍历指针变量i,j
        int k = left;                                                   // 待排序数组填充指针变量k

        while (i <= leftEnd && j <= rightEnd){                          // 任意子数组遍历完，推出循环
            if(tmp[i] < tmp[j])arr[k++] = tmp[i++];
            else arr[k++] = tmp[j++];
        }
        if(i <= leftEnd){                                               // 如果左数组还有剩余元素
            while (i < leftEnd)arr[k++] = tmp[i++];
        }
        if (j <= rightEnd){                                             // 如果右数组还有剩余元素
            while (j < rightEnd)arr[k++] = tmp[j++];
        }

    }
}
