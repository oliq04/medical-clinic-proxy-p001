package org.example.medicalclinicproxyp001.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MedicalProxyException extends RuntimeException {
    private final HttpStatus status;

    public MedicalProxyException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
