package com.example.employee.repository;

import com.example.employee.domin.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {

    Optional<Employee> findEmployeeByUsername(String username);
}
