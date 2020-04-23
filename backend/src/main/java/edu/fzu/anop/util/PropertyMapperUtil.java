package edu.fzu.anop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class PropertyMapperUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(PropertyMapperUtil.class);
    private static final Pattern GET_PATTERN = Pattern.compile("get\\w+");
    private static final String GET = "get";
    private static final String SET = "set";

    public static void map(Object source, Object target) {
        if (source == null || target == null) {
            throw new NullPointerException("source or target must not be null");
        }
        Class<?> aClass = source.getClass();
        Class<?> bClass = target.getClass();
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method getMethod = methods[i];
            if (GET_PATTERN.matcher(getMethod.getName()).matches()) {
                String setMethodName = getMethod.getName().replace(GET, SET);
                Class<?> returnType = getMethod.getReturnType();
                try {
                    Method setMethod = bClass.getMethod(setMethodName, returnType);
                    Object value = getMethod.invoke(source);
                    setMethod.invoke(target, value);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    LOGGER.info("mapping failed:" + e.getMessage());
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
            LOGGER.info("can not create instance of" + e.getMessage());
            return null;
        }
        map(source, target);
        return target;
    }

    public static boolean hasProperty(String name, Class<?> targetClass) {
        if (targetClass == null) {
            throw new NullPointerException("targetClass must not be null");
        }
        if (StringUtil.isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException("property must not be blank");
        }
        name = name.trim();
        if (Character.isLowerCase(name.charAt(0))) {
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
        try {
            Method property = targetClass.getMethod(GET + name.trim());
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static String getUnderscoreFormat(String source) {
        if (StringUtil.isNullOrWhiteSpace(source)) {
            throw new IllegalArgumentException("source must not be blank");
        }
        source = source.trim();
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < source.length(); i++) {
            if (!Character.isUpperCase(source.charAt(i))) {
                sb.append(source.charAt(i));
            } else {
                if (i != 0) {
                    sb.append('_');
                }
                sb.append(Character.toLowerCase(source.charAt(i)));
            }
        }
        return sb.toString();
    }
}
