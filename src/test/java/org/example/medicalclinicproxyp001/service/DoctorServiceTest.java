package org.example.medicalclinicproxyp001.service;

import org.example.medicalclinicproxyp001.client.MedicalClinicFeignClient;
import org.example.medicalclinicproxyp001.model.DoctorDto;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DoctorServiceTest {
    private DoctorService service;
    private MedicalClinicFeignClient medicalClinicFeignClient;

    @BeforeEach
    void setup() {
        this.medicalClinicFeignClient = Mockito.mock(MedicalClinicFeignClient.class);
        this.service = new DoctorService(medicalClinicFeignClient);
    }

    @Test
    void getDoctors_CorrectData_DoctorsOfGivenSpecializationReturned() {
        int page = 0;
        int size = 5;

        DoctorDto doctorDto = DoctorDto.builder()
                .id(1L)
                .firstName("Doctor")
                .lastName("Oekter")
                .specialization("CARDIOLOGY")
                .clinics(new ArrayList<>())
                .build();
        PageableDto<DoctorDto> pageWithDoctor = new PageableDto<>(size, page, 1, 1, List.of(doctorDto));

        when(medicalClinicFeignClient.doctorsOfSpecialization(0,5, "cardiology")).thenReturn(pageWithDoctor);
        PageableDto<DoctorDto> result = service.getDoctors(0, 5, "cardiology");

        assertThat(result).isEqualTo(pageWithDoctor);
        assertThat(result.getContent()).hasSize(1);
    }

}
