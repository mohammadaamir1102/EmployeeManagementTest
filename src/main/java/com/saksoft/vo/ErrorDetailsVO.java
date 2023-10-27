package com.saksoft.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDetailsVO {
    private String errorCode;
    private String errorMessage;
    private String result;
    private String requestURI;
    private HttpStatus httpStatus;
}
