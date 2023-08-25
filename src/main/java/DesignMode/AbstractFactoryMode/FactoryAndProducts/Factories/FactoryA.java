package DesignMode.AbstractFactoryMode.FactoryAndProducts.Factories;

import DesignMode.AbstractFactoryMode.FactoryAndProducts.Factory;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductA.FromFactoryAPdA;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductB.FromFactoryAPdB;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductC.FromFactoryAPdC;

/**
 * 具体工厂A，实现工厂接口
 */
public class FactoryA implements Factory {
    public Product getProduct(String pd) {
        switch (pd){
            case "FromFactoryAPdA":return new FromFactoryAPdA();
            case "FromFactoryAPdB":return new FromFactoryAPdB();
            case "FromFactoryAPdC":return new FromFactoryAPdC();
            default:return null;
        }
    }
}
