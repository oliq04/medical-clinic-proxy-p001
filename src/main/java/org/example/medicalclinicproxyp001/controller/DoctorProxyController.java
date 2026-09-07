package org.example.medicalclinicproxyp001.controller;

import lombok.RequiredArgsConstructor;
import org.example.medicalclinicproxyp001.model.DoctorDto;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.example.medicalclinicproxyp001.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequiredArgsConstructor
public class DoctorProxyController {

    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public PageableDto<DoctorDto> getDoctors(@RequestParam("specialization") String specialization
            , @RequestParam("page") int page, @RequestParam("size") int size) {
        return doctorService.getDoctors(page, size, specialization);
    }
}
