package com.tun.casestudy1.service;

import com.tun.casestudy1.entity.EmployeeRecord;
import com.tun.casestudy1.repository.EmployeeRecordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRecordService implements IService<EmployeeRecord>{

    EmployeeRecordRepository employeeRecordRepository;

    @Override
    public List<EmployeeRecord> findAll() {
        return employeeRecordRepository.findAll();
    }

    @Override
    public EmployeeRecord find(int id) {
        return employeeRecordRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        employeeRecordRepository.deleteById(id);
    }

    @Override
    public void save(EmployeeRecord employeeRecord) {
        employeeRecordRepository.save(employeeRecord);
    }

    public void update(int id, EmployeeRecord employeeRecord) {
        EmployeeRecord employeeRecord1 = employeeRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        employeeRecord1.setType(employeeRecord.getType());
        employeeRecord1.setReason(employeeRecord.getReason());
        employeeRecord1.setDate(employeeRecord.getDate());

        employeeRecordRepository.save(employeeRecord1);
    }
}
