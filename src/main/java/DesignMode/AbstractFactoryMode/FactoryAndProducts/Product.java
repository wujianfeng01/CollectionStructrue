package DesignMode.AbstractFactoryMode.FactoryAndProducts;

/**
 * 产品接口
 */
public interface Product {
    /**
     * 打印工厂信息
     */
    public void factoryMessage();

    /**
     * 打印产品信息
     */
    public void productMessage();
}
