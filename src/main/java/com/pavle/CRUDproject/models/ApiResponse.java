package com.pavle.CRUDproject.models;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private  T employeeResp;


    public ApiResponse(String message, int status, T employeeResp) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.employeeResp = employeeResp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEmployeeResp() {
        return employeeResp;
    }

    public void setEmployeeResp(T employeeResp) {
        this.employeeResp = employeeResp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
