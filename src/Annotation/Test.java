package Annotation;

import Annotation.Annotations.SelfAutowired;
import Annotation.Interface.Animal;
import Annotation.Interface.Impl.Cat;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 自定义注解测试类
 */
public class Test {
    @SelfAutowired(getObject = "Annotation.Interface.Impl.Cat")
    public  Animal cat;
    @SelfAutowired(getObject = "Annotation.Interface.Impl.Dog")
    public  Animal dog;

    public Test() throws Exception {
        // 通过AnnotationAnalysis.analysis()方法返回@SelfAutowired注解的实例list
        List<Object> objectList = AnnotationAnalysis.analysis("Annotation.Test");
        // 获取当前类的属性数组
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (int i = 0; i < objectList.size(); i++) {
            declaredFields[i].set(this,objectList.get(i));  // 将属性数组中的引用类型指向list中的实例
        }
    }
    public void printEat(){
        System.out.println(Test.class);
        cat.eat();
        dog.eat();
    }
    public void printThink(){
        System.out.println(Test.class);
        System.out.println(cat.think());
        System.out.println(dog.think());
    }

    public static void main(String[] args) {
        try{
            Test test = new Test();
            test.printEat();
            test.printThink();
        }catch (Exception ex){
            System.out.println(ex);
        }



    }
}
