package com.schoolmanagerserver.exception;

import com.schoolmanagerserver.pojos.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public <E> Result<E> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }
}

