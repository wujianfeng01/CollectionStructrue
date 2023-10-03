import org.junit.Test;

/**
 * try-catch-finally测试
 */
public class TryCatchFinallyTest {
    /**
     * 在这个方法中，当代码执行到 System.out.println(res/0);
     * 这行时，会抛出一个异常，因为不能除以零。由于异常被捕获，程序流程会进入 catch 块。

     * 尽管在 finally 块中也将 res 的值设为了 6，但是 finally 块中的代码会在 catch 块中的 return 语句之后执行。
     * 所以 res 被设为 7 后，在 finally 块中再次设置为 6
     * 但这个值并不会影响 catch 块中的 return 语句返回的值，因为 return 语句已经确定了返回值为 7。
     *
     * 在 catch 块中，你将 res 的值设为了 7，并且使用 return res; 返回了这个值。这就是为什么输出是 res = 7。
     * @return
     */
    public static int method1(){
        int res = 0;
        try {
            System.out.println(res/0);
        }catch (Exception ex){
            res = 7;
            return res;
        }finally {
            res = 60;
        }
        return res;
    }

    /**
     * 在 catch 块中，你将 res 的值设为了 7，并且使用 return res
     * 然后，finally 块中的代码会在 catch 块中的 return 语句之后执行。
     * 在 finally 块中，你将 res 的值设为了 6，并且使用 return res; 返回了这个值。
     * 这个返回语句会覆盖 catch 块中的返回值。因此，最终的输出结果是 res = 6
     * 因为 finally 块中的 return 语句的返回值取代了 catch 块中的返回值。
     * @return
     */
    public static int method2(){
        int res = 0;
        try {
            System.out.println(res/0);
        }catch (Exception ex){
            res = 7;
            return res;
        }finally {
            res = 6;
            return res;
        }
    }
    @Test
    public void test1(){
        System.out.println(method1());
    }
    @Test
    public void test2(){
        System.out.println(method2());
    }
}
