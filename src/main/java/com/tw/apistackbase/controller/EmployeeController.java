package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private List<Employee> employeeList = new ArrayList<>();

    @PostMapping(path = "/addEmployees", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addEmployees(@RequestBody List<Employee> employees){
        boolean isAdded = employeeList.addAll(employees);
        if(isAdded)
            return ResponseEntity.ok("Employees Added!");
        else
            return ResponseEntity.ok("Failed to add employees");
    }

    @GetMapping(path = "/getEmployees", produces = {"application/json"})
    public List<Employee> getEmployees() {
        return employeeList;
    }
    
}
