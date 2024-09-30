package com.tun.casestudy1.service;

import com.tun.casestudy1.dto.request.EmployeeSearchDto;
import com.tun.casestudy1.dto.response.EmployeeResponse;
import com.tun.casestudy1.entity.Employee;
import com.tun.casestudy1.repository.EmployeeRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService{

    EmployeeRepository employeeRepository;

    public List<EmployeeResponse> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = employees.stream().map(
                employee -> {
                    EmployeeResponse employeeResponse = EmployeeResponse.builder()
                            .id(employee.getId())
                            .name(employee.getName())
                            .gender(employee.getGender())
                            .imageUrl(employee.getImageUrl())
                            .dOB(employee.getDOB())
                            .salary(employee.getSalary())
                            .level(employee.getLevel())
                            .email(employee.getEmail())
                            .phoneNumber(employee.getPhoneNumber())
                            .note(employee.getNote())
                            .departmentId(employee.getDepartmentId())
                            .build();
                    return employeeResponse;
                }
        ).toList();
        return employeeResponses;
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void update(int id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        employee1.setName(employee.getName());
        employee1.setGender(employee.getGender());
        employee1.setImageUrl(employee.getImageUrl());
        employee1.setDOB(employee.getDOB());
        employee1.setSalary(employee.getSalary());
        employee1.setLevel(employee.getLevel());
        employee1.setEmail(employee.getEmail());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employee1.setNote(employee.getNote());

        employeeRepository.save(employee1);
    }

    public List<Employee> searchUser(EmployeeSearchDto searchDto) {
        return employeeRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchDto.getName() != null && !searchDto.getName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + searchDto.getName().toLowerCase() + "%"));
            }

            if (searchDto.getGender() != null) {
                predicates.add(cb.equal(root.get("gender"), searchDto.getGender()));
            }

            if (searchDto.getDOB() != null) {
                predicates.add(cb.equal(root.get("dOB"), searchDto.getDOB()));
            }

            if (searchDto.getSalary() != null) {
                predicates.add(cb.equal(root.get("salary"), searchDto.getSalary()));
            }

            if (searchDto.getLevel() != null) {
                predicates.add(cb.equal(root.get("level"), searchDto.getLevel()));
            }

            if (searchDto.getEmail() != null && !searchDto.getEmail().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + searchDto.getEmail().toLowerCase() + "%"));
            }

            if (searchDto.getPhoneNumber() != null && !searchDto.getPhoneNumber().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("phoneNumber")), "%" + searchDto.getPhoneNumber().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

}
