package sort;

/**
 * 计数排序
 * 遍历数组，找出数组中的最大数字，记为 max，然后创建一个长度为 max+1 的辅助数组 counter 。
 * 借助 counter 统计 nums 中各数字的出现次数，其中 counter[num] 对应数字 num 的出现次数。
 * 统计方法很简单，只需遍历 arr（设当前数字为 arr[i]），每轮将 counter[arr[i]] 增加 1 即可。
 * 由于 counter 的各个索引天然有序，因此相当于所有数字已经被排序好了。
 * 接下来，我们遍历 counter ，根据各数字的出现次数，将它们按从小到大的顺序填入 arr 即可。
 */
public class CounterSort {
    static void doSort(int[] arr){
        int max = findMax(arr);  // 统计数组最大元素 m
        int[] counter = new int[max+1];
        for (int i = 0; i < arr.length; i++) {  // 统计各数字的出现次数
            counter[arr[i]]++;
        }
        for (int i = 0,j = 0; i < counter.length; i++) {   // 遍历 counter，将各元素填入原数组 arr
            while (counter[i]>0){
                arr[j++] = i;
                counter[i]--;
            }
        }
    }
    // 找数组最大值
    static int findMax(int[] arr){
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>arr[max])max = i;
        }
        return arr[max];
    }
}
