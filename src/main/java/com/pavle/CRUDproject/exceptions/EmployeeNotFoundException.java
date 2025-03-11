package com.pavle.CRUDproject.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee with ID: "+id+" not found");
    }
}
