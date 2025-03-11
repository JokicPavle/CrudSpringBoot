package com.pavle.CRUDproject.services;

import com.pavle.CRUDproject.exceptions.EmployeeNotFoundException;
import com.pavle.CRUDproject.models.Employee;
import com.pavle.CRUDproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImp implements EmployeeService{

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public List<Employee> getAllEmployees() {
      return employeeRepository.findAll();
   }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> existedEmployee = employeeRepository.findById(id);
        if(existedEmployee.isPresent()) {
            Employee editedEmployee = existedEmployee.get();
            editedEmployee.setName(employee.getName());
            editedEmployee.setPosition(employee.getPosition());
            editedEmployee.setSalary(employee.getSalary());
            return employeeRepository.save(editedEmployee);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
