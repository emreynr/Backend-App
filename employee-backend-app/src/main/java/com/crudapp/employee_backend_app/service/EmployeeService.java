package com.crudapp.employee_backend_app.service;

import com.crudapp.employee_backend_app.entity.Employee;

import java.util.List;

public interface EmployeeService {

    //
    // Define CRUD methods
    //

    // Create an Employee
    Employee save(Employee saveEmployee);

    // Read an/all employees
    Employee findById(int employeeId);

    List<Employee> findAll();

    // Update an Employee with id
    Employee update(Employee updateEmployee);

    // Delete Employee with id
    void deleteById(int employeeId);
}
