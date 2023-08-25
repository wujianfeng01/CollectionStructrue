package DesignMode.ProxyMode.DynamicProxy.CGLIBProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 自定义操作
        System.out.println("before method："+method.getName());
        // 调用父类的方法
        Object invoke = methodProxy.invokeSuper(o,args);
        // 自定义操作
        System.out.println("after method："+method.getName());
        return invoke;
    }
}
