package Annotation;

import Annotation.Annotations.SelfAutowired;
import Annotation.Annotations.ValueInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于反射的属性注解解析类
 */
public class AnnotationAnalysisField {
    /**
     * 注解分析方法
     * @param className  需要进行注解分析的主类
     * @return
     * @throws Exception
     */
    static List<Object> doAnalysis(String className) throws Exception{
        Class<?> aClass = Class.forName(className);     // 获取需要分析注解的Class对象
        Field[] fields = aClass.getDeclaredFields();    // 获取类属性数组
        List<Object> list = new ArrayList<>();          // 结果链表
        // 自动装配方法
        autowiredFields(list,fields);
        return list;
    }

    /**
     * 自动装配方法实现
     * @param list    结果list
     * @param fields  属性数组
     * @throws ClassNotFoundException
     */
    private static void autowiredFields(List<Object> list,Field[] fields) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Field f : fields) {     // 获取所有属性变量
            Annotation[] declaredAnnotations = getAnnotation(f);   // 获取属性的所有注解

            if (declaredAnnotations!=null&&declaredAnnotations.length>=1){
                for (int i = 0; i < declaredAnnotations.length; i++) {
                    Annotation annotation = declaredAnnotations[i];
                    // 判断注解类型
                    if(annotation.annotationType() == SelfAutowired.class){
                        SelfAutowired selfAutowired = (SelfAutowired) annotation;
                        Object instance = Class.forName(selfAutowired.getObject()).newInstance();
                        list.add(instance);
                    }else if (annotation.annotationType() == ValueInfo.class){
                        ValueInfo valueInfo = (ValueInfo) annotation;
                        String version = valueInfo.version();
                        list.add(version);
                    }
                }
            }
        }
    }

    /**
     * 返回属性的注解数组
     * @param f
     * @return
     */
    private static Annotation[] getAnnotation(Field f){
        return f.getDeclaredAnnotations();

    }

}
