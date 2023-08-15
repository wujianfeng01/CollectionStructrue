package InheritedTest;

public class B extends A {
    public int val;
    @Override
    public void setVal(int val) {
        System.out.println("B类方法setVal().......");
        this.val = val*val;
    }

    @Override
    public int getVal() {
        System.out.println("B类方法getVal().......");
        return this.val;

    }
}
