package DesignMode.ProxyMode.DynamicProxy.JDKProxy;

import DesignMode.ProxyMode.DynamicProxy.JDKProxy.Interface.SomeInterface;

/**
 * JDK动态代理只能代理实现了接口的类或者直接代理接口
 * 通过实现接口拦截被代理类调用的方法
 */
public class JDKDynamicTest {
    public static void main(String[] args) {
        // 获取代理对象
        SomeInterface proxyObject = (SomeInterface) ProxyFactory.getProxyObject(new SomeInterfaceImpl());
        // 调用代理方法
        proxyObject.printMsg("hello world","wujianfeng");
        // 代理对象实现的接口
        Class<?>[] interfaces = ProxyFactory.getProxyObject(new SomeInterfaceImpl()).getClass().getInterfaces();
        for (Class clazz: interfaces){
            System.out.println(clazz.getName());
        }
    }
}
