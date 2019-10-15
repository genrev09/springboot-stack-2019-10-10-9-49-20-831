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

    @DeleteMapping(path = "/removeEmployee/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable(value = "id") int employeeID) {
        String message = "Employee id "+employeeID + " not found";
        Employee terminatedEmployee = getEmployeeByID(employeeID);
        if (terminatedEmployee != null){
            employeeList.remove(terminatedEmployee);
            message = "You have successfully removed employee "+terminatedEmployee.getName();
        }
        return ResponseEntity.ok(message);
    }

    private Employee getEmployeeByID(@PathVariable("id") int employeeID) {
        return employeeList.stream().filter(employee -> employee.getId() == employeeID).findFirst().orElse(null);
    }
    
}
