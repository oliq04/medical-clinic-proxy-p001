package org.example.medicalclinicproxyp001.service;

import org.example.medicalclinicproxyp001.client.MedicalClinicFeignClient;
import org.example.medicalclinicproxyp001.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class VisitServiceTest {
    private VisitService visitService;
    private MedicalClinicFeignClient medicalClinicFeignClient;

    @BeforeEach
    void setup() {
        medicalClinicFeignClient = Mockito.mock(MedicalClinicFeignClient.class);
        visitService = new VisitService(medicalClinicFeignClient);
    }

    @Test
    void getPatientVisits_CorrectData_PageOfVisitsReturned() {
        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .idCardNo(333L)
                .firstName("Tomasz")
                .lastName("Drozd")
                .phoneNumber("443332")
                .birthday(LocalDateTime.of(2004, 4, 2, 1, 0, 0))
                .build();
        DoctorDto doctorDto = DoctorDto.builder()
                .id(1L)
                .firstName("Doctor")
                .lastName("Oekter")
                .specialization("CARDIOLOGY")
                .clinics(new ArrayList<>())
                .build();

        ClinicDto clinicDto = ClinicDto.builder()
                .id(1L)
                .name("Clinic1")
                .town("Warsaw")
                .postCode("022-33")
                .address("Polna 1")
                .build();

        VisitDto visitDto = VisitDto.builder()
                .id(1L)
                .startDate(LocalDateTime.of(2026, 2, 3, 12, 30, 0))
                .endDate(LocalDateTime.of(2026, 2, 3, 12, 45, 0))
                .patient(patientDto)
                .doctor(doctorDto)
                .clinic(clinicDto)
                .build();

        PageableDto<VisitDto> pageableDto = new PageableDto<>(1, 0, 1, 1, List.of(visitDto));
        when(medicalClinicFeignClient.patientVisits(1L, 0, 5)).thenReturn(pageableDto);

        PageableDto<VisitDto> result = visitService.getPatientVisits(1L, 0, 5);

        assertThat(result).isEqualTo(pageableDto);
    }

    @Test
    void assignPatientToVisit_CorrectData_VisitsDtoReturned() {
        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .idCardNo(333L)
                .firstName("Tomasz")
                .lastName("Drozd")
                .phoneNumber("443332")
                .birthday(LocalDateTime.of(2004, 4, 2, 1, 0, 0))
                .build();
        DoctorDto doctorDto = DoctorDto.builder()
                .id(1L)
                .firstName("Doctor")
                .lastName("Oekter")
                .specialization("CARDIOLOGY")
                .clinics(new ArrayList<>())
                .build();

        ClinicDto clinicDto = ClinicDto.builder()
                .id(1L)
                .name("Clinic1")
                .town("Warsaw")
                .postCode("022-33")
                .address("Polna 1")
                .build();

        VisitDto visitDto = VisitDto.builder()
                .id(1L)
                .startDate(LocalDateTime.of(2026, 2, 3, 12, 30, 0))
                .endDate(LocalDateTime.of(2026, 2, 3, 12, 45, 0))
                .patient(patientDto)
                .doctor(doctorDto)
                .clinic(clinicDto)
                .build();


        when(medicalClinicFeignClient.assignToVisit(1L, 1L)).thenReturn(visitDto);

        VisitDto result = visitService.assignPatientToVisit(1L, 1L);

        assertThat(result).isEqualTo(visitDto);
    }

    @Test
    void availableVisitsAssignedToDoctorBySpecAndDate_CorrectData_PageableVisitDtoReturned() {
        int page = 0;
        int size = 5;
        String specialization = "CARDIOLOGY";
        LocalDate fromDate = LocalDate.of(2027, 8, 15);
        LocalDate toDate = LocalDate.of(2027, 8, 20);

        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .idCardNo(333L)
                .firstName("Tomasz")
                .lastName("Drozd")
                .phoneNumber("443332")
                .birthday(LocalDateTime.of(2004, 4, 2, 1, 0, 0))
                .build();

        DoctorDto doctorDto = DoctorDto.builder()
                .id(1L)
                .firstName("Doctor")
                .lastName("Oekter")
                .specialization("CARDIOLOGY")
                .clinics(new ArrayList<>())
                .build();

        ClinicDto clinicDto = ClinicDto.builder()
                .id(1L)
                .name("Clinic1")
                .town("Warsaw")
                .postCode("022-33")
                .address("Polna 1")
                .build();

        VisitDto visitDto = VisitDto.builder()
                .id(1L)
                .startDate(LocalDateTime.of(2027, 8, 15, 12, 30, 0))
                .endDate(LocalDateTime.of(2027, 8, 15, 12, 45, 0))
                .patient(patientDto)
                .doctor(doctorDto)
                .clinic(clinicDto)
                .build();

        PageableDto<VisitDto> pageWithVisit = new PageableDto<>(size, page, 1, 1, List.of(visitDto));

        when(medicalClinicFeignClient.visitsOfSpecializationAndDate(page, size, fromDate, toDate, specialization))
                .thenReturn(pageWithVisit);

        PageableDto<VisitDto> result = visitService.availableVisitsAssignedToDoctorBySpecAndDate(
                page, size, specialization, fromDate, toDate);

        assertThat(result).isEqualTo(pageWithVisit);
        assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void visitsAssignedToDoctor_CorrectData_PageableVisitDtoReturned() {
        Long doctorId = 1L;
        int page = 0;
        int size = 5;

        DoctorDto doctorDto = DoctorDto.builder()
                .id(doctorId)
                .firstName("Doctor")
                .lastName("Oekter")
                .specialization("CARDIOLOGY")
                .clinics(new ArrayList<>())
                .build();

        ClinicDto clinicDto = ClinicDto.builder()
                .id(1L)
                .name("Clinic1")
                .town("Warsaw")
                .postCode("022-33")
                .address("Polna 1")
                .build();

        VisitDto visitDto = VisitDto.builder()
                .id(1L)
                .startDate(LocalDateTime.of(2027, 8, 15, 12, 30, 0))
                .endDate(LocalDateTime.of(2027, 8, 15, 12, 45, 0))
                .patient(null)
                .doctor(doctorDto)
                .clinic(clinicDto)
                .build();

        PageableDto<VisitDto> pageWithVisit = new PageableDto<>(size, page, 1, 1, List.of(visitDto));

        when(medicalClinicFeignClient.allVisitsOfDoctor(doctorId, page, size))
                .thenReturn(pageWithVisit);

        PageableDto<VisitDto> result = visitService.visitsAssignedToDoctor(doctorId, page, size);

        assertThat(result).isEqualTo(pageWithVisit);
        assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void cancelVisit_CorrectData_VisitDtoReturned() {
        Long visitId = 1L;

        PatientDto patientDto = PatientDto.builder()
                .id(1L)
                .idCardNo(333L)
                .firstName("Tomasz")
                .lastName("Drozd")
                .phoneNumber("443332")
                .birthday(LocalDateTime.of(2004, 4, 2, 1, 0, 0))
                .build();

        DoctorDto doctorDto = DoctorDto.builder()
                .id(1L)
                .firstName("Doctor")
                .lastName("Oekter")
                .specialization("CARDIOLOGY")
                .clinics(new ArrayList<>())
                .build();

        ClinicDto clinicDto = ClinicDto.builder()
                .id(1L)
                .name("Clinic1")
                .town("Warsaw")
                .postCode("022-33")
                .address("Polna 1")
                .build();

        VisitDto visitDto = VisitDto.builder()
                .id(visitId)
                .startDate(null)
                .endDate(null)
                .patient(patientDto)
                .doctor(doctorDto)
                .clinic(clinicDto)
                .build();

        when(medicalClinicFeignClient.cancelVisit(visitId)).thenReturn(visitDto);

        VisitDto result = visitService.cancelVisit(visitId);

        assertThat(result).isEqualTo(visitDto);
    }
}
