package com.tun.casestudy1.repository;

import com.tun.casestudy1.entity.EmployeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRecordRepository extends JpaRepository<EmployeeRecord, Integer> {
}
