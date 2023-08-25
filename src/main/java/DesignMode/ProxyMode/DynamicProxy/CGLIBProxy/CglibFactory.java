package DesignMode.ProxyMode.DynamicProxy.CGLIBProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 *  CGLIB代理工厂，创建代理对象。
 */
public class CglibFactory {
    public static Object getProxy(Class clazz){
        // 创建动态代理增强类对象
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new MyMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}
