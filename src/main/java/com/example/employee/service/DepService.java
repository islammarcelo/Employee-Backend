package com.example.employee.service;

import com.example.employee.repository.DepRepo;
import com.example.employee.domin.Department;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepService {
    @Autowired
    private final DepRepo depRepo;

    public void addDepartment(String name, int code) {
        Optional<Department> department = depRepo.findDepartmentByCode(code);
        if (department.isPresent()) {
            throw new IllegalStateException("code is taken");
        }
        depRepo.save(Department.builder().name(name).code(code).build());
    }

    public List<Department> getAllDepartments() {return depRepo.findAll();}

    public void deleteDepartment(int id) {
        if (!depRepo.existsById(id)) {
            throw new IllegalStateException("Department with this id " + id + " is not exist");
        }
        depRepo.deleteById(id);
    }

    @Transactional
    public void updateDepartment(int id, int code, String name) {
        Department department = depRepo.findById(id).orElseThrow(() -> new IllegalStateException(
                "Department with this id " + id + " is not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(department.getName(), name)) {
            department.setName(name);
        }

        if (code != 0 && !Objects.equals(department.getCode(), code)) {
            if (depRepo.findDepartmentByCode(code).isPresent()) {
                throw new IllegalStateException("code is taken");
            }
            department.setCode(code);
        }


    }

    public Department getDepartment(int depId) {
        Department department = depRepo.findById(depId).orElseThrow(() -> new IllegalStateException(
            "Department with this id " + depId + " is not exist"));
        return department;
    }

}
