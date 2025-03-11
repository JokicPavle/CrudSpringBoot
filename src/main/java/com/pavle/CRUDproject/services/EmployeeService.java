package com.pavle.CRUDproject.services;


import com.pavle.CRUDproject.models.Employee;
import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    Employee addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> findEmployeeById(Long Id);
    void deleteEmployee(Long Id);
    Employee updateEmployee(Long Id, Employee employee );
}
