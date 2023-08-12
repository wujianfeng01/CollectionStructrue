package UnSafe;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 不是BootCLassLoader加载的类获取Unsafe类对象
 */
public class NotBootClassLoaderGetUnsafe {
    /**
     * 通过反射获取Unsafe单例对象
     * @return
     */
    public static Unsafe ReflectGetUnsafe() {
        try {
            Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe.setAccessible(true);
            return (Unsafe) unsafe.get(null);
        } catch (IllegalAccessException | NoSuchFieldException|RuntimeException e) {
            return null;
        }

    }

    public static void main(String[] args) {
        try {
            Unsafe.getUnsafe();  // 直接获取Unsafe单例对象会抛出异常
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        Unsafe reflectGetUnsafe = NotBootClassLoaderGetUnsafe.ReflectGetUnsafe();
        System.out.println(reflectGetUnsafe);
    }
}
