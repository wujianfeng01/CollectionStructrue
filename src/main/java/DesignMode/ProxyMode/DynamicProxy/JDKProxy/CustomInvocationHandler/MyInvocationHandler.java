package DesignMode.ProxyMode.DynamicProxy.JDKProxy.CustomInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义InvocationHandler类
 */
public class MyInvocationHandler implements InvocationHandler {
    Object target;
    public MyInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before method："+method.getName());
        Object invokeResult = method.invoke(target, args);           // 调用被代理类的原生方法
        System.out.println("after method："+method.getName());
        return invokeResult;

    }
}
