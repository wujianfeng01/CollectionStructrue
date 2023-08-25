package DesignMode.ProxyMode.DynamicProxy.CGLIBProxy;

/**
 * CGLIB动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用。
 * 因此不能代理声明为 final 类型的类和方法。
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        // 创建代理对象
        SomeClass proxy = (SomeClass) CglibFactory.getProxy(SomeClass.class);
        // 调用
        proxy.printClassMessage();
        // 代理对象的父类
        Class<?> superclass = CglibFactory.getProxy(SomeClass.class).getClass().getSuperclass();
        System.out.println(superclass.getName());
    }
}
