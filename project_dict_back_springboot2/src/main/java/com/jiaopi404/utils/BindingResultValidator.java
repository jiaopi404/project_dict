package com.jiaopi404.utils;

import com.jiaopi404.config.exception.GraceException;
import com.jiaopi404.config.exception.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类，BindingResult 的 验证器，抛出 ValidationException 错误
 */
public class BindingResultValidator {

    /**
     * Validate.
     *
     * @param bindingResult the binding result
     * @throws ValidationException the validation exception
     */
    public static void validate (BindingResult bindingResult) throws ValidationException {
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        if (!map.isEmpty()) {
            throw GraceException.validationError(map, "表单验证错误");
        }
    }
}
