package org.example.medicalclinicproxyp001.feign;

import org.example.medicalclinicproxyp001.model.DoctorDto;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.example.medicalclinicproxyp001.model.VisitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient(name = "medicalclinic")
public interface MedicalClinicFeignClient {

    @GetMapping("/visit/patient/{id}")
    PageableDto<VisitDto> patientVisits(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("size") int size);

    @PostMapping("/visit/patient")
    VisitDto assignToVisit(@RequestParam(value = "patient-id") Long patientId, @RequestParam(value = "visit-id") Long visitId);

    @GetMapping("/visit/doctor/{id}/available")
    PageableDto<VisitDto> visitsAvailableAssignedToDoctor(@PathVariable Long id, @RequestParam(value = "page") int page,
                                                          @RequestParam int size);

    @GetMapping("/visit/doctor")
    PageableDto<VisitDto> visitsOfSpecializationAndDate(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("from-date") LocalDate fromDate,
                                                        @RequestParam(required = false, value = "to-date") LocalDate toDate,
                                                        @RequestParam(required = false, value = "specialization") String specialization);

    @GetMapping("/doctors")
    PageableDto<DoctorDto> doctorsOfSpecialization(@RequestParam("page") int page, @RequestParam("size") int size,
                                                   @RequestParam("specialization") String specialization);

    @GetMapping("/visit/doctor/{id}/all")
    PageableDto<VisitDto> allVisitsOfDoctor(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("size") int size);

    @PatchMapping("/visit/{id}")
    VisitDto cancelVisit(@PathVariable Long id);
}
