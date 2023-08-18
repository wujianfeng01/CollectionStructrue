package Annotation;
@ValueInfo(name = "吴剑峰", age = 25)   // 使用注解
public class Person {
    String name;
    int age;

    /**
     * 基于反射获取注解值
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void printName() throws ClassNotFoundException, NoSuchFieldException {
        ValueInfo valueInfo = Class.forName("Annotation.Person").getAnnotation(ValueInfo.class);
        this.name = valueInfo.name();
        this.age = valueInfo.age();
        System.out.println("name：" + name + " " + "age：" + age);
    }
}
