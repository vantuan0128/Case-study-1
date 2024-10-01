package com.tun.casestudy1.service;

import com.tun.casestudy1.entity.Department;
import com.tun.casestudy1.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IService<Department>{

    DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department find(int id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    public void update(int id, String name) {
        Department department1 = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        department1.setName(name);

        departmentRepository.save(department1);
    }
}
