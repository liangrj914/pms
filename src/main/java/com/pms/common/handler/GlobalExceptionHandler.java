package com.pms.common.handler;

import com.pms.common.enums.ResponseCode;
import com.pms.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

/**
 * @author liangrj
 * @since 2023/03/02/ 21:24
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 参数校验失败处理
     * @param e 异常类型
     * @return 参数错误提示
     */
    @ExceptionHandler(value = {Exception.class})
    public Response handleException(Exception e) {
        Response response = new Response();
        response.setCode(ResponseCode.PARAM_EXCEPTION.getCode());
        String msg = null;
        if(e instanceof MissingServletRequestParameterException){
            msg = MessageFormat.format("缺少参数{0}", ((MissingServletRequestParameterException) e).getParameterName());
        }
        if(e instanceof ConstraintViolationException){
            Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) e).getConstraintViolations();
            if(!CollectionUtils.isEmpty(sets)){
                StringBuilder sb = new StringBuilder();
                sets.forEach(error -> {
                    if (error instanceof FieldError) {
                        sb.append(((FieldError)error).getField()).append(":");
                    }
                    sb.append(error.getMessage()).append(";");
                });
                msg = sb.toString();
                msg = StringUtils.substring(msg, 0, msg.length() -1);
            }
        }
        if (e instanceof BindException){
            List<ObjectError> errors = ((BindException) e).getBindingResult().getAllErrors();
            msg = getValidExceptionMsg(errors);
        }
        if (e instanceof MethodArgumentNotValidException){
            List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            msg = getValidExceptionMsg(errors);
        }
        if (StringUtils.isNotBlank(msg)) {
            response.setMessage(msg);
        } else {
            log.error("系统异常：", e);
            response.setMessage("系统错误，请联系管理员");
        }
        return response;
    }

    private String getValidExceptionMsg(List<ObjectError> errors) {
        if(!CollectionUtils.isEmpty(errors)){
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> {
                if (error instanceof FieldError) {
                    sb.append(((FieldError)error).getField()).append(":");
                }
                sb.append(error.getDefaultMessage()).append(";");
            });
            String msg = sb.toString();
            msg = StringUtils.substring(msg, 0, msg.length() -1);
            return msg;
        }
        return null;
    }
}
