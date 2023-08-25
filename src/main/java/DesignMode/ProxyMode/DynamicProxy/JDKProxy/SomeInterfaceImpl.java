package DesignMode.ProxyMode.DynamicProxy.JDKProxy;

import DesignMode.ProxyMode.DynamicProxy.JDKProxy.Interface.SomeInterface;

public class SomeInterfaceImpl implements SomeInterface {
    @Override
    public void printMsg(String message, String fromUser) {
        System.out.println(message+" form "+ fromUser);
    }
}
