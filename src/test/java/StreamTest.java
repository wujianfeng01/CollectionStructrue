import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    List<String> list = Arrays.asList("A", "B", "C");
    @Test
    public void test1(){
        Stream<String> stream = list.stream().filter(s -> "A".equals(s));
        System.out.println(stream.count());
    }

    /**
     * 遍历打印元素
     */
    @Test
    public void test2(){
        list.stream().forEach(System.out::println);
    }

    /**
     * limit获取到1个元素
     */
    @Test
    public void test3(){
        Stream<String> limit = list.stream().limit(1);
        String[] array = limit.toArray(String[]::new);
        System.out.println(array.length);

    }

    /**
     * map 对每个元素就行操作并返回新的流
     */
    @Test
    public void test4(){
        Stream<String> stream = list.stream().map(s -> s + "22");
        stream.forEach(System.out::println);
    }

    /**
     * 流排序
     */
    @Test
    public void test5(){
        list.stream().sorted().forEach(System.out::println);
    }

    /**
     * 将符合条件的流元素放入List
     */
    @Test
    public void test6(){
        List<String> collect = list.stream().filter(t->"B".equals(t)).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    /**
     * 把list转为string，各元素用，号隔开
     */
    @Test
    public void test7(){
        String s1 = list.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        System.out.println(s1);
    }

    /**
     * 一个Stream只能操作一次，不能断开，否则会报错。
     */
    @Test
    public void test8(){
        Stream<String> stream = list.stream();
        try {
            stream.map(map->map+"1");   // 第一次使用
            stream.sorted();            // 第二次使用
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    /**
     * 一个流可以连续使用
     */
    @Test
    public void test9(){
        Stream<String> stream = list.stream();
        try {
            stream.map(map->map+"1").sorted().forEach(System.out::println);
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    /**
     * 对数组元素做统计
     */
    @Test
    public void test10(){
        Integer[] arr = {1, 2, 5, 4};
        List<Integer> number = Arrays.asList(arr);
        IntSummaryStatistics statistics = number.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());
        System.out.println(statistics.getCount());
    }


}
