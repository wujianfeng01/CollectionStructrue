package DesignMode.FactoryMode;

/**
 * 工厂模式
 */
public class FactoryMode {
    /**
     * 获取产品实例
     * @param pd
     * @return
     */
    public static Product getPd(String pd) {
        switch (pd){
            case "PD_A":return new ProductA();
            case "PD_B":return new ProductB();
            case "PD_C":return new ProductC();
            default:
                System.out.println("参数错误.....");
        }
        return null;
    }
}
