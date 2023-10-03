import org.junit.Test;

public class StringObjectTest {
    @Test
    public void Test_String_intern_Method(){
        String s1 = "Java";
        String s2 = s1.intern();
        String s3 = new String("Java");
        String s4 = s3.intern();
        // s1 和 s2 指向的是堆中的同一个对象
        System.out.println(s1 == s2); // true
        // s3 和 s4 指向的是堆中不同的对象
        System.out.println(s3 == s4); // false
        // s1 和 s4 指向的是堆中的同一个对象
        System.out.println(s1 == s4); //true

    }

    /**
     * 引用的值在程序编译期是无法确定的，编译器无法对其进行优化
     */
    @Test
    public void Test_String_Operator(){
        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        String str5 = "string";
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false

    }

    /**
     * final引用的值在程序编译期是确定的，编译器可以对其进行优化
     */
    @Test
    public void Test_String_Operator_final(){
        final String str1 = "str";
        final String str2 = "ing";
        // 下面两个表达式其实是等价的
        String c = "str" + "ing"; // 常量池中的对象
        String d = str1 + str2;   // 常量池中的对象
        System.out.println(c == d);// true

    }

    /**
     * final引用但是需要在运行期间才能获取值，编译器无法优化
     */
    @Test
    public void Test_String_Operator_final2(){
        final String str1 = "str";
        final String str2 = getStr();
        // 下面两个表达式其实是等价的
        String c = "str" + "ing";// 常量池中的对象
        String d = str1 + str2; // 常量池中的对象
        System.out.println(c == d);// true
    }

    private String getStr() {
        return "ing";
    }

}
