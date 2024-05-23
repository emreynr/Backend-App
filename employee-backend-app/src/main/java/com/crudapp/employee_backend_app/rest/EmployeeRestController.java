package com.crudapp.employee_backend_app.rest;


import com.crudapp.employee_backend_app.entity.Employee;
import com.crudapp.employee_backend_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee == null) {
            throw new RuntimeException("Invalid employee id : " + tempEmployee);
        }
        return tempEmployee;
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee savedEmployee) {

        savedEmployee.setId(0); // just in case, set curret employee's id for 0.

        Employee dbEmployee = employeeService.save(savedEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee updatedEmployee) {

        Employee dbEmployee = employeeService.save(updatedEmployee);

        return updatedEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null) {
            throw new RuntimeException("Employee not found with provided id : " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted Employee id : " + employeeId;
    }
}
