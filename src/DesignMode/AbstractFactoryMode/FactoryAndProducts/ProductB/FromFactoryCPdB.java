package DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductB;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;

public class FromFactoryCPdB implements Product {
    @Override
    public void factoryMessage() {
        System.out.print("it  is  from factoryC，");
    }

    @Override
    public void productMessage() {
        factoryMessage();
        System.out.println("it is productB");
    }
}
