package com.tun.casestudy1.dto.response;

import com.tun.casestudy1.entity.Department;
import com.tun.casestudy1.entity.EmployeeRecord;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    int id;

    String name;

    int gender;

    String imageUrl;

    LocalDate dOB;

    int salary;

    int level;

    String email;

    String phoneNumber;

    String note;

    int departmentId;
}
