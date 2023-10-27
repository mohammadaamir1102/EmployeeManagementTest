package com.saksoft.exception;


import com.saksoft.util.EMConstant;
import com.saksoft.vo.ErrorDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler({EMException.class})
    public ResponseEntity<Object> handleEMException(EMException emException, HttpServletRequest httpServletRequest) {
        ErrorDetailsVO errorDetailsVO = new ErrorDetailsVO();
        errorDetailsVO.setErrorCode(emException.getErrorCode());
        errorDetailsVO.setErrorMessage(emException.getErrorMessage());
        errorDetailsVO.setRequestURI(httpServletRequest.getRequestURI());
        errorDetailsVO.setResult(EMConstant.CALL_RESULT_FAILURE);
        errorDetailsVO.setHttpStatus(emException.getHttpStatus());
        log.error(emException.getMessage(), emException);
        return ResponseEntity.accepted().body(errorDetailsVO);
    }
}
