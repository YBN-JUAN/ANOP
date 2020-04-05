package edu.fzu.anop.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static <T> T getLoginUser(Class<T> tClass) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal.getClass().equals(tClass)) {
            return (T) principal;
        }
        return null;
    }
}
