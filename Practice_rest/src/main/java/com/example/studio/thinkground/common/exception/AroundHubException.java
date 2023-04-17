package com.example.studio.thinkground.common.exception;

import com.example.studio.thinkground.common.Constants;
import org.springframework.http.HttpStatus;

public class AroundHubException extends Exception{
    private static final long SerialVersionUID = 4663380435091151694L;

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public AroundHubException(Constants.ExceptionClass exceptionClass ,HttpStatus httpStatus, String message){
        super(exceptionClass.toString()+message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass(){
        return exceptionClass;
    }

    public int getHttpStatusCode() { return httpStatus.value(); }

    public String getHttpStatusType(){
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
