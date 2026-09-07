package org.example.medicalclinicproxyp001.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class PatientDto {
    private Long id;
    private Long idCardNo;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime birthday;
}
