package org.example.medicalclinicproxyp001.controller;

import lombok.RequiredArgsConstructor;
import org.example.medicalclinicproxyp001.controller.search.SearchVisitParameters;
import org.example.medicalclinicproxyp001.model.PageableDto;
import org.example.medicalclinicproxyp001.model.VisitDto;
import org.springframework.web.bind.annotation.*;
import org.example.medicalclinicproxyp001.service.VisitService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visit")
public class VisitProxyController {

    private final VisitService visitService;

    @GetMapping("/search")
    public PageableDto<VisitDto> visitsSearch(SearchVisitParameters searchVisitParameters) {
        return visitService.visitSearch(searchVisitParameters);
    }

    @PatchMapping("/{id}")
    public VisitDto cancelVisit(@PathVariable Long id) {
        return visitService.cancelVisit(id);
    }

    @PostMapping("/patient")
    public VisitDto assignToVisit(@RequestParam("patient-id") Long patientId, @RequestParam("visit-id") Long visitId) {
        return visitService.assignPatientToVisit(patientId, visitId);
    }
}
