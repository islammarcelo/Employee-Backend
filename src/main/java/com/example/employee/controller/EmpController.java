package com.example.employee.controller;


import com.example.employee.config.MassageResponse;
import com.example.employee.domin.Employee;
import com.example.employee.service.EmpService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/employee")
public class EmpController {
    @Autowired
    private final EmpService empService;

    @PostMapping("/add-employee")
    public ResponseEntity addEmployee(@RequestBody Employee employee){

        empService.addEmployee(employee.getUsername(),employee.getAge());
        return ResponseEntity.status(HttpStatus.OK).body(new MassageResponse("Added new employee successfully"));
    }
    @PostMapping("/add-department-to-employee")
    public ResponseEntity addDepartmentToEmployee(@RequestBody EmployeeDepartment employeeDepartment){
        //System.out.println("hereeeee"+employeeDepartment.department.toString());
        empService.addDepartmentToEmployee(employeeDepartment.getUsername(),employeeDepartment.getDepartment());
        return ResponseEntity.status(HttpStatus.OK).body(new MassageResponse("Added department successfully"));
    }

    @GetMapping("/get-all-employees")
    public List<Employee> getAllEmployees(){
        return empService.getAllEmployees();
    }

    @GetMapping("/get-employee/{empId}")
    public Employee getEmployee(@PathVariable("empId") int empId){
        return empService.getEmployee(empId);
    }

    @DeleteMapping(path = "/delete-employee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable("empId") int empId){
        empService.deleteEmployee(empId);
        return ResponseEntity.status(HttpStatus.OK).body(new MassageResponse("Deleted employee successfully"));
    }

    @PutMapping(path = "/update-employee/{empId}")
    public ResponseEntity updateEmployee(@PathVariable("empId") int empId,
                                         @RequestBody Employee  employee){
        empService.updateEmployee(empId,employee.getAge(),employee.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new MassageResponse("Updated employee successfully"));
    }

}

@AllArgsConstructor
@Getter
@Setter
class EmployeeDepartment {
    String username;
    String department;

}
