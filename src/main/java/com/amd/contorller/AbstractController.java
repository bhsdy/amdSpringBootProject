package com.amd.contorller;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Created by zengzl on 2017/7/1.
 */
public abstract class AbstractController {

  protected Logger log = LoggerFactory.getLogger(getClass());

  @Autowired(required = false)
  protected HttpServletRequest request;

  @Autowired(required = false)
  protected HttpServletResponse response;

  @Autowired(required = false)
  protected HttpSession session;

  @Autowired(required = false)
  protected ServletContext application;

  //异常回调 HTTP 500
  protected ResponseEntity callbackFail(String msg) {
    log.error(msg);
    return new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  //成功回调 HTTP 200
  protected ResponseEntity callbackSuccess(Object obj) {
    return new ResponseEntity(obj, HttpStatus.OK);
  }

  //业务异常回调 HTTP 200
  protected ResponseEntity callBusinessError(Map<String, Object> errorMap) {
    log.debug(JSON.toJSONString(errorMap));
    return new ResponseEntity(errorMap, HttpStatus.OK);
  }

  //业务异常回调 HTTP 400
  protected ResponseEntity callBusinessError(String errorMsg) {
    log.debug(errorMsg);
    return new ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST);
  }

  //参数校验回调
  protected ResponseEntity callParamValidError(BindingResult bindingResult) {
    Map<String, Object> errorMap = bindingResult.getFieldErrors()
        .stream()
        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    return callBusinessError(errorMap);
  }


}
