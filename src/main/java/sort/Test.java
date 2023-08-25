package sort;

public class Test {
    public static void main(String[] args) {
        int[] arr = {5,4,2,1,0,3,7,3,6,3,8,0,2,1,9,9};
        BucketSort.doSort(arr);
        for (int i :
                arr) {
            System.out.print(i+" ");
        }
    }
}
