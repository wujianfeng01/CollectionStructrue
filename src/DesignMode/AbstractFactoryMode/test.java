package DesignMode.AbstractFactoryMode;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.Factory;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;

/**
 * 抽象工厂测试类
 */
public class test {
    public static void main(String[] args) {
        // 获取不同工厂
        Factory factoryA = AbstractFactoryMode.getFactory("InheritedTest.A");
        Factory factoryB = AbstractFactoryMode.getFactory("InheritedTest.B");
        Factory factoryC = AbstractFactoryMode.getFactory("C");
        // 获取不同工厂下的A产品
        Product fromFactoryAPdA = factoryA.getProduct("FromFactoryAPdA");
        Product fromFactoryBPdA = factoryB.getProduct("FromFactoryBPdA");
        Product fromFactoryCPdA = factoryC.getProduct("FromFactoryCPdA");
        // 获取不同工厂下的B产品
        Product fromFactoryAPdB = factoryA.getProduct("FromFactoryAPdB");
        Product fromFactoryBPdB = factoryB.getProduct("FromFactoryBPdB");
        Product fromFactoryCPdB = factoryC.getProduct("FromFactoryCPdB");

        // 打印产品信息
        try {
            fromFactoryAPdA.productMessage();
            fromFactoryBPdA.productMessage();
            fromFactoryCPdA.productMessage();

            fromFactoryAPdB.productMessage();
            fromFactoryBPdB.productMessage();
            fromFactoryCPdB.productMessage();



        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }
}
