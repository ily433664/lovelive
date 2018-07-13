package com.lovelive.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @author dhe
 * @Description: 对象复制工具
 */
public class CopyUtils {

    /**
     * 一个类把属性值赋值给另一个类的相同的属性
     */
    public static void copyObject(Object source, Object dest) throws Exception {
        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for (int i = 0; i < sourceProperty.length; i++) {
                for (int j = 0; j < destProperty.length; j++) {
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())
                            && sourceProperty[i].getPropertyType() == destProperty[j].getPropertyType()) {
                        // 调用source的getter方法和dest的setter方法
                        if (sourceProperty[i].getWriteMethod() != null) {
                            destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
    }

    /**
     * 一个类把属性值赋值给另一个类的相同的属性,不复制propertyNames的属性
     */
    public static void copyObjectNotProperty(Object source, Object dest, String... propertyNames) throws Exception {

        if (propertyNames == null || propertyNames.length == 0) {
            copyObject(source, dest);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : propertyNames) {
            sb.append(value).append(",");
        }
        String property = sb.toString();

        // 获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for (int i = 0; i < sourceProperty.length; i++) {
                for (int j = 0; j < destProperty.length; j++) {
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())
                            && sourceProperty[i].getPropertyType() == destProperty[j].getPropertyType()) {
                        // 调用source的getter方法和dest的setter方法
                        if (!property.contains(sourceProperty[i].getName())
                                && sourceProperty[i].getWriteMethod() != null) {
                            destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
    }

}