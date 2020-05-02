package edu.fzu.anop.util;

/**
 * String检查帮助工具
 *
 * @author Xue_Feng
 */
public class StringUtil {
    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
