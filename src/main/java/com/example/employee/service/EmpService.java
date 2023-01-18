package com.example.employee.service;

import com.example.employee.domin.Department;
import com.example.employee.domin.Employee;
import com.example.employee.repository.DepRepo;
import com.example.employee.repository.EmpRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmpService {

    @Autowired
    private final EmpRepo empRepo;
    private final DepRepo depRepo;

    public void addEmployee(String username, int age){
        Optional<Employee> employee = empRepo.findEmployeeByUsername(username);
        if(employee.isPresent()){throw new IllegalStateException("Username is taken");}
        empRepo.save(Employee.builder()
                .username(username)
                .age(age).build());
    }

    @Transactional
    public void addDepartmentToEmployee(String username, String departmentName){
        Optional<Employee> employee = empRepo.findEmployeeByUsername(username);
        Optional<Department> department = depRepo.findDepartmentByName(departmentName);
        if(!employee.isPresent()){throw new IllegalStateException("Username is incorrect");}
        if(!department.isPresent()){throw new IllegalStateException("Department is incorrect");}
        employee.get().setDepartment(department.get());

    }
    
    public List<Employee> getAllEmployees(){return empRepo.findAll();}

    public Employee getEmployee(int empId) {
        Employee employee = empRepo.findById(empId).orElseThrow(()-> new IllegalStateException(
                "Employee with this id "+empId+" is not exist"));
        return employee;
    }

    public void deleteEmployee(int id){
        if(!empRepo.existsById(id)){throw new IllegalStateException("User with this id "+id+" is not exist");}
        empRepo.deleteById(id);
    }

    @Transactional
    public void updateEmployee(int id, int age, String username){
        Employee employee = empRepo.findById(id).orElseThrow(()-> new IllegalStateException(
                "Employee with this id "+id+" is not exist"));
        if(username != null && username.length() > 0 && !Objects.equals(employee.getUsername(),username)){
            if(empRepo.findEmployeeByUsername(username).isPresent()){
                throw new IllegalStateException("username is taken");}
            employee.setUsername(username);
        }
        if (age != 0 && !Objects.equals(employee.getAge(),age)){employee.setAge(age);}
    }




}
