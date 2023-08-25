package sort;

/**
 * 插入排序
 * 初始状态下，数组的第 1 个元素已完成排序。
 * 选取数组的第 2 个元素作为 base ，将其插入到正确位置后，数组的前 2 个元素已排序。
 * 选取第 3 个元素作为 base ，将其插入到正确位置后，数组的前 3 个元素已排序。
 * 以此类推，在最后一轮中，选取最后一个元素作为 base ，将其插入到正确位置后，所有元素均已排序。
 */
public class InsertSort {
    public static void doSort(int[] arr){
        if(arr.length==0||arr.length==1)return;
        for (int i = 1; i < arr.length; i++) {     // 外循环：排序第i个元素
            int base = arr[i];                     // 待插入元素值
            int j = i-1;
            for (;j >= 0; j--) {                   // 内循环：在前i个有序部分中，找到插入位置
                if (base < arr[j]) {
                    arr[j+1] = arr[j];             // 向后移动元素
                } else {                           // 当base >= arr[j-1]，找到插入位置
                    break;
                }
            }
            arr[j+1] = base;                       // j+1位置插入，break
        }
    }
}
