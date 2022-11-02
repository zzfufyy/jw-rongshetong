package com.shopping.wx.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.function.Supplier;

/**
 * @author ljy
 * @date 2022-03-14 9:23
 */
@Slf4j
public class ReflectionUtils {

    /**
     * TODO: 使用 对象 的 setter 来设置值，而不是直接设置字段
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @param onAbsent  在缺失时设置值
     * @return 操作是否成功
     */
    public static boolean setFieldIfAbsent(Object object, String fieldName, Supplier<Object> onAbsent) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            // 只对 还未 设置的字段进行设置
            if (field.get(object) == null) {
                field.set(object, onAbsent.get());
            }
            return true;
        } catch (Exception e) {
            log.debug("Setting {} field in object typed {} failed because of {}", fieldName, object.getClass(), e);
            return false;
        }
    }

    /**
     * TODO: 使用 对象 的 setter 来设置值，而不是直接设置字段
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @param value     需要设置的值
     * @return 操作是否成功
     */
    public static boolean setField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
            return true;
        } catch (Exception e) {
            log.debug("Setting {} field in object typed {} failed because of {}", fieldName, object.getClass(), e);
            return false;
        }
    }


}
