package org.example.medicalclinicproxyp001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedicalClinicProxyP001Application {

    public static void main(String[] args) {
        SpringApplication.run(MedicalClinicProxyP001Application.class, args);
    }

}
