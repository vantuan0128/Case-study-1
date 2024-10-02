package com.tun.casestudy1.repository.specifications;

import com.tun.casestudy1.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecifications {
    public static Specification<Employee> searchByQuery(String searchValue) {
        return (root, query, cb) -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return cb.conjunction();
            }

            String likeQuery = "%" + searchValue.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("name")), likeQuery),
                    cb.like(cb.lower(root.get("phoneNumber")), likeQuery),
                    cb.like(cb.lower(root.get("department").get("name")), likeQuery),
                    cb.like(cb.lower(cb.literal(String.valueOf(root.get("salary")))), likeQuery),
                    cb.like(cb.lower(cb.literal(String.valueOf(root.get("level")))), likeQuery),
                    cb.like(cb.function("DATE_FORMAT", String.class, root.get("dOB"), cb.literal("%d/%m/%Y")), likeQuery)
            );

        };
    }
}

