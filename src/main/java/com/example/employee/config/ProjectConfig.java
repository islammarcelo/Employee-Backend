package com.example.employee.config;

import com.example.employee.service.DepService;
import com.example.employee.service.EmpService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    CommandLineRunner commandLineRunnerUser(EmpService empService, DepService depService){
        return args -> {
           depService.addDepartment("CS",101);
           depService.addDepartment("IS",102);
           empService.addEmployee("islam",23);
           empService.addEmployee("ahmed",22);
        //    empService.addDepartmentToEmployee("islam", "CS");
        //    empService.addDepartmentToEmployee("ahmed", "IS");

        };
    }
}
