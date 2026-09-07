package org.example.medicalclinicproxyp001.service;

import org.example.medicalclinicproxyp001.client.MedicalClinicFeignClient;
import lombok.RequiredArgsConstructor;
import org.example.medicalclinicproxyp001.controller.search.SearchVisitParameters;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.example.medicalclinicproxyp001.model.VisitDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final MedicalClinicFeignClient medicalClinicFeignClient;

    public VisitDto assignPatientToVisit(Long patientId, Long visitId) {
        return medicalClinicFeignClient.assignToVisit(patientId, visitId);
    }

    public PageableDto<VisitDto> visitSearch(SearchVisitParameters searchVisitParameters) {
        return medicalClinicFeignClient.visitsSearch(searchVisitParameters);
    }

    public VisitDto cancelVisit(Long id) {
        return medicalClinicFeignClient.cancelVisit(id);
    }
}
