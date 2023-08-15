package sort;

public class Test {
    public static void main(String[] args) {
        int[] arr = {2,5,6,1,4,0,2,3,5,6,7,8,9,0,1};
        CounterSort.doSort(arr);
        for (int i :
                arr) {
            System.out.print(i+" ");
        }
    }
}
