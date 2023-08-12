package DesignMode.AbstractFactoryMode.FactoryAndProducts.Factories;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Factory;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.Product;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductA.FromFactoryBPdA;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductB.FromFactoryBPdB;
import DesignMode.AbstractFactoryMode.FactoryAndProducts.ProductC.FromFactoryBPdC;

/**
 * 具体工厂B，实现工厂接口
 */
public class FactoryB implements Factory {
    public Product getProduct(String pd) {
        switch (pd){
            case "FromFactoryBPdA":return new FromFactoryBPdA();
            case "FromFactoryBPdB":return new FromFactoryBPdB();
            case "FromFactoryBPdC":return new FromFactoryBPdC();
            default:return null;
        }
    }
}
