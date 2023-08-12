package DesignMode.FactoryMode;

/**
 * 产品B，实现产品接口
 */
public class ProductB implements Product{
    @Override
    public void productContext() {
        System.out.println("I am is ProductB");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
