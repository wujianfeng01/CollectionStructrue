package sort;

public class Test {
    public static void main(String[] args) {
        int[] arr = {5,4,2,1,0,3};
        QuickSort.doSort(arr);
        for (int i :
                arr) {
            System.out.print(i+" ");
        }
    }
}
