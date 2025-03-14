package com.pavle.CRUDproject.models;

public class EmployeeRespDTO {
    private Long id;
    private String name;
    private String position;
    private Double salary;

    // Constructors
    public EmployeeRespDTO() {}

    public EmployeeRespDTO(Long id, String name, String position, Double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}

