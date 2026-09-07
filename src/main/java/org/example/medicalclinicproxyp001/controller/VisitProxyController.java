package org.example.medicalclinicproxyp001.controller;

import lombok.RequiredArgsConstructor;
import org.example.medicalclinicproxyp001.model.DoctorDto;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.example.medicalclinicproxyp001.model.VisitDto;
import org.springframework.web.bind.annotation.*;
import org.example.medicalclinicproxyp001.service.VisitService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visit")
public class VisitProxyController {

    private final VisitService visitService;

    @GetMapping("/patient/{id}")
    public PageableDto<VisitDto> getPatientVisits(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return visitService.getPatientVisits(id, page, size);
    }

    @PostMapping("/patient")
    public VisitDto assignToVisit(@RequestParam("patient-id") Long patientId, @RequestParam("visit-id") Long visitId) {
        return visitService.assignPatientToVisit(patientId, visitId);
    }

    @GetMapping("/doctor/available/{id}")
    public PageableDto<VisitDto> availableVisitsAssignedToDoctor(@PathVariable Long id, @RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        return visitService.availableVisitsAssignedToDoctor(id, page, size);
    }

    @GetMapping("/doctor")
    public PageableDto<VisitDto> visitsOfSpecializationAndDate(@RequestParam("page") int page,
                                                               @RequestParam("size") int size,
                                                               @RequestParam("from-date") LocalDate fromDate,
                                                               @RequestParam(required = false, value = "to-date") LocalDate toDate,
                                                               @RequestParam(required = false, value = "specialization") String specialization
    ) {
        return visitService.availableVisitsAssignedToDoctorBySpecAndDate(page, size, specialization, fromDate, toDate);
    }

    @GetMapping("/doctors/{id}/all")
    public PageableDto<VisitDto> visitsAssignedToDoctor(@PathVariable Long id, @RequestParam("page") int page,
                                                        @RequestParam("size") int size) {
        return visitService.visitsAssignedToDoctor(id, page, size);
    }

    @PatchMapping("/{id}")
    public VisitDto cancelVisit(@PathVariable Long id) {
        return visitService.cancelVisit(id);
    }
}
