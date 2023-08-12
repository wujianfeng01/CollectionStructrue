package DesignMode.AbstractFactoryMode.FactoryAndProducts.Factories;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Factory;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductA.FromFactoryCPdA;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductB.FromFactoryCPdB;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductC.FromFactoryCPdC;

/**
 * 具体工厂C，实现工厂接口
 */
public class FactoryC implements Factory {
    public Product getProduct(String pd) {
        switch (pd){
            case "FromFactoryCPdA":return new FromFactoryCPdA();
            case "FromFactoryCPdB":return new FromFactoryCPdB();
            case "FromFactoryCPdC":return new FromFactoryCPdC();
            default:return null;
        }
    }
}
