package DesignMode.AbstractFactoryMode.FactoryAndProducts;

/**
 * 工厂接口
 */
public interface Factory {
    /**
     * 获取该工厂下的产品
     * @param pd
     * @return
     */
    public Product getProduct(String pd);
}
