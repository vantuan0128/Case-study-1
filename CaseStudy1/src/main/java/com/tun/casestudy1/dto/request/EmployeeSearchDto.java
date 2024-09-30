package com.tun.casestudy1.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchDto {
    String name;

    Integer gender;

    LocalDate dOB;

    Integer salary;

    Integer level;

    String email;

    String phoneNumber;
}
