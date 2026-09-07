package org.example.medicalclinicproxyp001.client;

import org.example.medicalclinicproxyp001.client.config.MedicalClinicFeignConfig;
import org.example.medicalclinicproxyp001.controller.search.SearchVisitParameters;
import org.example.medicalclinicproxyp001.model.DoctorDto;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.example.medicalclinicproxyp001.model.VisitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "medicalclinic", configuration = MedicalClinicFeignConfig.class)
public interface MedicalClinicFeignClient {

    @PostMapping("/visit/patient")
    VisitDto assignToVisit(@RequestParam(value = "patient-id") Long patientId, @RequestParam(value = "visit-id") Long visitId);

    @DeleteMapping("/visit/{id}")
    VisitDto cancelVisit(@PathVariable Long id);

    @GetMapping("/visit/search")
    PageableDto<VisitDto> visitsSearch(@SpringQueryMap SearchVisitParameters searchVisitParameters);

    @GetMapping("/doctors")
    PageableDto<DoctorDto> getDoctors(@RequestParam("page") int page, @RequestParam("size") int size,
                         @RequestParam("specialization") String specialization);
}
