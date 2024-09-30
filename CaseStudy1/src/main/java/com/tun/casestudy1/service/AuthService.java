package com.tun.casestudy1.service;

import com.tun.casestudy1.dto.request.LoginDto;
import com.tun.casestudy1.entity.Employee;
import com.tun.casestudy1.enums.Role;
import com.tun.casestudy1.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    EmployeeRepository employeeRepository;

    public String authenticate(String username, String password) {
        Employee employee = employeeRepository.findByEmail(username);

        if (employee == null || !employee.getPassword().equals(password)) {
            return null;
        }

        if (employee.getRole() == Role.ADMIN) {
            return "adminHome";
        } else if (employee.getRole() == Role.USER) {
            return "userHome";
        }

        return null;
    }
}
