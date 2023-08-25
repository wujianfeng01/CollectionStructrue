package DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductA;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;

/**
 * 产品A
 * 来自工厂C
 */
public class FromFactoryCPdA implements Product {
    @Override
    public void factoryMessage() {
        System.out.print("it  is  from factoryC，");
    }

    @Override
    public void productMessage() {
        factoryMessage();
        System.out.println("it is productA");
    }
}
