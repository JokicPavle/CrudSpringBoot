package com.pavle.CRUDproject.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException{
    public EmployeeAlreadyExistsException() {
        super("Employee already exists!");
    }
}
