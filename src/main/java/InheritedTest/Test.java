package InheritedTest;

/**
 * 当一个对象调用方法时，实际上是根据该对象的实际类型来决定应该调用哪个类的方法。
 * 成员变量的访问是基于引用的类型，而不是基于实际对象的类型。
 */
public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.setVal(2);                        //  子类的方法getVal()，由对象的实际类型决定。
        System.out.println(a.val);          //  父类的成员变量val，由引用类型A决定。
        System.out.println(a.getVal());     //  子类的方法getVal()，由对象的实际类型决定。

        B b = new B();
        b.setVal(2);                        //  子类的方法getVal()，由对象的实际类型决定。
        System.out.println(b.val);          //  子类的成员变量val，由引用类型B决定。
        System.out.println(b.getVal());     //  子类的方法getVal()，由对象的实际类型决定。

    }
}
