package edu.fzu.anop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class ShallowMapperUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ShallowMapperUtil.class);
    private static final String GET_PATTERN = "get\\w+";

    public static void map(Object source, Object target) {
        if (source == null || target == null) {
            throw new NullPointerException("source or target must not be null");
        }
        Class<?> aClass = source.getClass();
        Class<?> bClass = target.getClass();
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method getMethod = methods[i];
            if (Pattern.matches(GET_PATTERN, getMethod.getName())) {
                String setMethodName = getMethod.getName().replace("get", "set");
                Class<?> returnType = getMethod.getReturnType();
                try {
                    Method setMethod = bClass.getMethod(setMethodName, returnType);
                    Object value = getMethod.invoke(source);
                    setMethod.invoke(target, value);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    LOGGER.info("Exception in mapping:" + e.getMessage(), source, target);
                }
            }
        }
    }

    public static <T> T map(Object source, Class<T> targetClass) {
        if (targetClass == null) {
            throw new NullPointerException("targetClass must not be null");
        }
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.info("Exception in mapping:" + e.getMessage(), source, targetClass);
            return null;
        }
        map(source, target);
        return target;
    }
}
