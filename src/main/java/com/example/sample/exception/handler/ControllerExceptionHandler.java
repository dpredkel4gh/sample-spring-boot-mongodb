package com.example.sample.exception.handler;

import com.example.sample.exception.CommonErrorDetail;
import com.example.sample.exception.ServiceRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ServiceRuntimeException.class)
    public ResponseEntity handleResourceHandleException(ServiceRuntimeException exception, HttpServletRequest request) {
        CommonErrorDetail commonErrorDetail = prepareBuilder(exception, request)
                .status(exception.getStatus())
                .build();
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(commonErrorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class, DataAccessException.class})
    public ResponseEntity handleResourceHandleException(Exception exception, HttpServletRequest request) {
        CommonErrorDetail commonErrorDetail = prepareBuilder(exception, request)
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Data handling error")
                .build();
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(commonErrorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonErrorDetail> handleException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        CommonErrorDetail commonErrorDetail = prepareBuilder(exception, request)
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Validation error")
                .build();
        return new ResponseEntity<>(commonErrorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<CommonErrorDetail> handleException(Exception exception, HttpServletRequest request) {
        CommonErrorDetail commonErrorDetail = prepareBuilder(exception, request)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Internal application error")
                .build();
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(commonErrorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private CommonErrorDetail.CommonErrorDetailBuilder prepareBuilder(Throwable exception, HttpServletRequest request) {
        return CommonErrorDetail.builder()
                .timestamp(new Date().getTime())
                .detail(exception.getMessage())
                .exception(exception.getClass().getName())
                .method(request.getMethod())
                .requestedPath(request.getServletPath());
    }

}