package org.example.medicalclinicproxyp001.service;

import lombok.RequiredArgsConstructor;
import org.example.medicalclinicproxyp001.client.MedicalClinicFeignClient;
import org.example.medicalclinicproxyp001.model.DoctorDto;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final MedicalClinicFeignClient medicalClinicFeignClient;

    public PageableDto<DoctorDto> getDoctors(int page, int size, String specialization) {
        return medicalClinicFeignClient.doctorsOfSpecialization(page, size, specialization);
    }
}
