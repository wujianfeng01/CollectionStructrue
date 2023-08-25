package DesignMode.ProxyMode.DynamicProxy.JDKProxy;

import DesignMode.ProxyMode.DynamicProxy.JDKProxy.CustomInvocationHandler.MyInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * JDK代理对象工厂类创建代理对象
 */
public class ProxyFactory {
    public static Object getProxyObject(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), // 被代理类的类加载器
                target.getClass().getInterfaces(),                        // 被代理类实现的接口
                new MyInvocationHandler(target));                         // 代理类调用方法时用到的handler
    }

}
