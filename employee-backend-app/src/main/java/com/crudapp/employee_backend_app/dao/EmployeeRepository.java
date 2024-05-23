package com.crudapp.employee_backend_app.dao;

import com.crudapp.employee_backend_app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
