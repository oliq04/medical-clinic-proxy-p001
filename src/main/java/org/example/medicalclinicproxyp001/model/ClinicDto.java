package org.example.medicalclinicproxyp001.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ClinicDto {
    private Long id;
    private String name;
    private String town;
    private String postCode;
    private String address;
}
