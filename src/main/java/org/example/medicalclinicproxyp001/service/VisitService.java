package org.example.medicalclinicproxyp001.service;

import org.example.medicalclinicproxyp001.client.MedicalClinicFeignClient;
import lombok.RequiredArgsConstructor;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.example.medicalclinicproxyp001.model.VisitDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final MedicalClinicFeignClient medicalClinicFeignClient;

    public PageableDto<VisitDto> getPatientVisits(Long id, int page, int size) {
        return medicalClinicFeignClient.patientVisits(id, page, size);
    }

    public VisitDto assignPatientToVisit(Long patientId, Long visitId) {
        return medicalClinicFeignClient.assignToVisit(patientId, visitId);
    }

    public PageableDto<VisitDto> availableVisitsAssignedToDoctor(Long id, int page, int size) {
        return medicalClinicFeignClient.visitsAvailableAssignedToDoctor(id, page, size);
    }

    public PageableDto<VisitDto> availableVisitsAssignedToDoctorBySpecAndDate(int page, int size, String specialization,
                                                                              LocalDate fromDate, LocalDate toDate) {
        return medicalClinicFeignClient.visitsOfSpecializationAndDate(page, size, fromDate, toDate, specialization);
    }

    public PageableDto<VisitDto> visitsAssignedToDoctor(Long id, int page, int size) {
        return medicalClinicFeignClient.allVisitsOfDoctor(id, page, size);
    }

    public VisitDto cancelVisit(Long id) {
        return medicalClinicFeignClient.cancelVisit(id);
    }
}
