package com.crudapp.employee_backend_app.service;

import com.crudapp.employee_backend_app.dao.EmployeeRepository;
import com.crudapp.employee_backend_app.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    // define reference for jpa repository
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }


    @Override
    public Employee save(Employee saveEmployee) {

        return employeeRepository.save(saveEmployee);
    }

    @Override
    public Employee findById(int employeeId) {

        Optional<Employee> result = employeeRepository.findById(employeeId);

        Employee tempEmployee = null;

        if(result.isPresent()) {
            tempEmployee = result.get();
        }
        else {
            throw new RuntimeException("The employee didn't found with provided id : " + employeeId);
        }
        return tempEmployee;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee updateEmployee) {
        return employeeRepository.save(updateEmployee);
    }

    @Override
    public void deleteById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
