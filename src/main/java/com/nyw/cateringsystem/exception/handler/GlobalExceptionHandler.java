package com.nyw.cateringsystem.exception.handler;

import com.nyw.cateringsystem.consts.CodeEnum;
import com.nyw.cateringsystem.exception.AlreadyExistException;
import com.nyw.cateringsystem.exception.SourceNotFoundException;
import com.nyw.cateringsystem.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className GlobalExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SourceNotFoundException.class)
    public R sourceNotFoundExceptionHandler(SourceNotFoundException e) {
        return R.fail(e.getMessage(),CodeEnum.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public R alreadyExistExceptionHandler(AlreadyExistException e){
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public R dataAccessExceptionHandler(DataAccessException e){
        log.error("数据库操作异常",e);
        return R.fail(e.getRootCause().getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e){
        log.error(e.getMessage(),e);
        return R.fail(e.getMessage(),CodeEnum.FAIL);
    }

}
