package edu.fzu.anop.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class BindingResultUtil {
    public static List<String> getErrorList(BindingResult bindingResult) {
        List<String> errorList = new ArrayList<>(bindingResult.getFieldErrorCount());
        StringBuilder sb = new StringBuilder(48);
        for (FieldError error : bindingResult.getFieldErrors()) {
            sb.setLength(0);
            sb.append(error.getField());
            sb.append(":");
            sb.append(error.getDefaultMessage());
            errorList.add(sb.toString());
        }
        return errorList;
    }
}
