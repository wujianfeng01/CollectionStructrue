package InheritedTest;

public class A  {
    public int val;
    public void setVal(int val){
        System.out.println("A类方法setVal().......");
        this.val = val;
    }
    public int getVal(){
        System.out.println("A类方法getVal().......");
        return this.val;
    }
}
