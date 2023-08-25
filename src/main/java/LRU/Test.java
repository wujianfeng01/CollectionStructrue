package LRU;

public class Test {
    public static void main(String[] args) {
        LRU<Integer> lru = new LRU<Integer>(5);
        for (int i = 0; i < 10; i++) {
            lru.put(i);
        }
        for (Object i : lru.linkedList) {
            Integer e = (Integer) i;
            System.out.println(e.intValue());

        }
    }
}
