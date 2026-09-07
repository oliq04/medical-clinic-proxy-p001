package org.example.medicalclinicproxyp001.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private List<ClinicDto> clinics;
}