package org.example.medicalclinicproxyp001.client.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.example.medicalclinicproxyp001.exception.ErrorMessage;
import org.example.medicalclinicproxyp001.exception.MedicalProxyException;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class MedicalProxyErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            ErrorMessage errorResponse = objectMapper.readValue(bodyIs, ErrorMessage.class);
            return new MedicalProxyException(errorResponse.getHttpStatus(), errorResponse.getError());
        } catch (IOException e) {
            return defaultErrorDecoder.decode(methodKey, response);
        }
    }
}

