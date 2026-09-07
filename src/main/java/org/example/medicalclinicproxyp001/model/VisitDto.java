package org.example.medicalclinicproxyp001.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class VisitDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PatientDto patient;
    private DoctorDto doctor;
    private ClinicDto clinic;
}