package com.tun.casestudy1.service;

import com.tun.casestudy1.dto.request.EmployeeSearchDto;
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
public class EmployeeService implements IService<Employee>{

    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee find(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateAccount(int id, String name, String email, String password) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        employee1.setName(name);
        employee1.setEmail(email);
        employee1.setPassword(password);

        employeeRepository.save(employee1);
    }

    public void updateEmployee(int id, String name, String email, String password) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        employee1.setName(name);
        employee1.setEmail(email);
        employee1.setPassword(password);

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
