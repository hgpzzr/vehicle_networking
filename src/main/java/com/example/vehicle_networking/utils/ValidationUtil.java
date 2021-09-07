package com.example.vehicle_networking.utils;



import com.example.dangerous_goods.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author hgp
 * @version 1.0
 * @date 2020/12/14 12:49
 */
@Slf4j
@ControllerAdvice
public class ValidationUtil {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVO handleEx(BindException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for(FieldError fe: fieldErrors){
            sb.append("属性:").append(fe.getField()).append("校验不通过:").append(fe.getDefaultMessage()).append("; ");
        }
        log.info("参数错误 : "+sb);
        return ResultVOUtil.error(sb);
    }
}
