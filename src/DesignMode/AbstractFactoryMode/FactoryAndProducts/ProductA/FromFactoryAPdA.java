package DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductA;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;

/**
 * 产品A
 * 来自工厂A
 */
public class FromFactoryAPdA implements Product {

    @Override
    public void factoryMessage() {
        System.out.print("it  is  from factoryA，");
    }

    @Override
    public void productMessage() {
        factoryMessage();
        System.out.println("it is productA");
    }
}
