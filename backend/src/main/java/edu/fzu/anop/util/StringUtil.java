package edu.fzu.anop.util;

public class StringUtil {
    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
