package com.saksoft.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EMException extends Exception {
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

}
