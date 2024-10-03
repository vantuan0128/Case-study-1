package com.tun.casestudy1.service;

import com.tun.casestudy1.dto.request.UpdateAccountDto;
import com.tun.casestudy1.dto.request.UpdateEmployeeDto;
import com.tun.casestudy1.entity.Employee;
import com.tun.casestudy1.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
        Employee employee1 = Employee.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .level(employee.getLevel())
                .phoneNumber(employee.getPhoneNumber())
                .note(employee.getNote())
                .imageUrl(employee.getImageUrl())
                .dOB(employee.getDOB())
                .departmentId(employee.getDepartmentId())
                .build();
        employeeRepository.save(employee1);
    }

    public void updateAccount(int id, UpdateAccountDto employee) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee1.setPassword(employee.getPassword());

        employeeRepository.save(employee1);
    }

    public void updateEmployee(int id, UpdateEmployeeDto employee) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        employee1.setName(employee.getName());
        employee1.setLevel(employee.getLevel());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employee1.setSalary(employee.getSalary());
        employee1.setDepartmentId(employee.getDepartmentId());

        employeeRepository.save(employee1);
    }

    public List<Employee> searchUser(String query) {
        return employeeRepository.searchByQuery(query);
    }

}
