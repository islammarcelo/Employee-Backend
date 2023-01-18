package com.example.employee.repository;

import com.example.employee.domin.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepRepo extends JpaRepository<Department, Integer> {
    Optional<Department> findDepartmentByName(String name);
    Optional<Department> findDepartmentByCode(int code);
}
