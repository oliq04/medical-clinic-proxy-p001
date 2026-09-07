package org.example.medicalclinicproxyp001.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MedicalProxyExceptionHandler {
    @ExceptionHandler(MedicalProxyException.class)
    public ResponseEntity<ErrorMessage> handleMedicalProxyException(MedicalProxyException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorMessage(LocalDateTime.now(), ex.getMessage(), ex.getStatus()));
    }
}
