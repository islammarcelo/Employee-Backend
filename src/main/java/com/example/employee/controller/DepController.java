package com.example.employee.controller;

import com.example.employee.config.MassageResponse;
import com.example.employee.service.DepService;
import com.example.employee.domin.Department;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/department")
public class DepController {
    @Autowired
    private final DepService depService;

    @PostMapping("/add-department")
    public ResponseEntity addDepartment(@RequestBody Department department){
        depService.addDepartment(department.getName(),department.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(new MassageResponse("Added new department successfully"));
    }

    @GetMapping("/get-all-departments")
    public List<Department> getAllDepartments(){
        return depService.getAllDepartments();
    }

    @GetMapping("/get-department/{depId}")
    public Department getDepartment(@PathVariable("depId")int depId){
        return depService.getDepartment(depId);
    }

    @DeleteMapping(path = "/delete-department/{depId}")
    public ResponseEntity deleteDepartment(@PathVariable("depId") int depId){
        depService.deleteDepartment(depId);
        return ResponseEntity.status(HttpStatus.OK).body(new MassageResponse("Deleteed department successfully"));
    }

    @PutMapping(path = "/update-department/{depId}")
    public ResponseEntity updateEmployee(@PathVariable("depId") int depId,
                                        @RequestBody Department department){
        depService.updateDepartment(depId,department.getCode(),department.getName());
        return ResponseEntity.status(HttpStatus.OK).body(new MassageResponse("Updated department successfully"));
    }

}
