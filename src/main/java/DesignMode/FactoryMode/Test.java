package DesignMode.FactoryMode;

/**
 * 工厂模式测试
 */
public class Test {
    public static void main(String[] args) {

        Product pdA = FactoryMode.getPd("PD_A");
        Product pdB = FactoryMode.getPd("PD_B");
        Product pdC = FactoryMode.getPd("PD_C");
        Product pdD = FactoryMode.getPd("PD_D");
        try {
            pdA.productContext();
            pdB.productContext();
            pdC.productContext();
            pdD.productContext();
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }
}
