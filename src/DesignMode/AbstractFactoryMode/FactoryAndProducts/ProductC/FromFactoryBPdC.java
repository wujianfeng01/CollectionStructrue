package DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductC;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;

public class FromFactoryBPdC implements Product {

    @Override
    public void factoryMessage() {
        System.out.print("it  is  from factoryB，");
    }

    @Override
    public void productMessage() {
        factoryMessage();
        System.out.println("it is productC");
    }
}
