package DesignMode.AbstractFactoryMode;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.*;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Factories.FactoryA;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Factories.FactoryB;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Factories.FactoryC;

/**
 * 抽象工厂模式顶层类AbstractFactoryMode
 */
public class AbstractFactoryMode {
    /**
     * 静态方法获取工厂
     * @param factory
     * @return
     */
    public static Factory getFactory(String factory) {
        switch (factory){
            case "InheritedTest.A":return new FactoryA();
            case "InheritedTest.B":return new FactoryB();
            case "C":return new FactoryC();
            default:return null;
        }
    }
}
