package DesignMode.FactoryMode;

/**
 * 产品C，实现产品接口
 */
public class ProductC implements Product{
    @Override
    public void productContext() {
        System.out.println("I am is ProductC");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
