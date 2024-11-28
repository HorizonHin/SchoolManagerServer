package com.schoolmanagerserver.exception;

import com.schoolmanagerserver.pojos.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public <E> Result<E> handleException(Exception e) {
        logger.error("发生异常：", e);
        String errorMessage = StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败";
        return Result.fail(errorMessage);
    }

    @ExceptionHandler(NullPointerException.class)
    public <E> Result<E> handleNullPointerException(NullPointerException e) {
        logger.error("发生空指针异常：{}",e.getMessage() , e);
        return Result.fail("空指针异常;"+e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public <E> Result<E> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("非法参数异常：", e);
        return Result.fail("非法参数：" + e.getMessage());
    }
}

