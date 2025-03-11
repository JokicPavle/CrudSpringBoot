package com.pavle.CRUDproject.controllers;

import com.pavle.CRUDproject.exceptions.EmployeeNotFoundException;
import com.pavle.CRUDproject.models.Employee;
import com.pavle.CRUDproject.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



        @GetMapping
        public ResponseEntity<List<Employee>> getAllEmployees() {
            List<Employee> employees = employeeService.getAllEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
            Employee newEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseThrow(() -> new EmployeeNotFoundException(id));
    }


    @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if(employee.isPresent()) {
            employeeService.deleteEmployee(id);
            return  ResponseEntity.ok("Employee deleted succesfully!");
        } else {
            throw new EmployeeNotFoundException(id);
        }
        }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);

            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
