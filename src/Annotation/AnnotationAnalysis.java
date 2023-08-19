package Annotation;

import Annotation.Annotations.SelfAutowired;
import Annotation.Interface.Animal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解分析类
 */
public class AnnotationAnalysis {
    /**
     * 注解分析方法
     * @param className  需要进行注解分析的主类
     * @return
     * @throws Exception
     */
    static List<Object> analysis(String className) throws Exception{
        Class<?> aClass = Class.forName(className);     // 获取需要分析注解的Class对象
        Field[] fields = aClass.getDeclaredFields();
        List<Object> list = new ArrayList<>();
        for (Field f : fields) {     // 获取所有属性变量
            Annotation[] declaredAnnotations = f.getDeclaredAnnotations();   // 获取属性的所有注解
            if (declaredAnnotations!=null&&declaredAnnotations.length>=1){
                for (int i = 0; i < declaredAnnotations.length; i++) {
                    Annotation annotation = declaredAnnotations[i];
                    // 判断注解类型
                    if(annotation.annotationType() == SelfAutowired.class){
                        SelfAutowired selfAutowired = (SelfAutowired) annotation;
                        Object instance = Class.forName(selfAutowired.getObject()).newInstance();
                        list.add(instance);
                    }
                }
            }

        }
        return list;
    }
}
