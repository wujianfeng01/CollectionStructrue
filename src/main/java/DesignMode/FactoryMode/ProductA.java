package DesignMode.FactoryMode;

/**
 * 产品A，实现产品接口
 */
public class ProductA implements Product {
    @Override
    public void productContext() {
        System.out.println("I am is ProductA");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
