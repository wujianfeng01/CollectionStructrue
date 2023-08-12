package DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductB;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;

public class FromFactoryBPdB implements Product {

    @Override
    public void factoryMessage() {
        System.out.print("it  is  from factoryB，");
    }

    @Override
    public void productMessage() {
        factoryMessage();
        System.out.println("it is productB");
    }
}
