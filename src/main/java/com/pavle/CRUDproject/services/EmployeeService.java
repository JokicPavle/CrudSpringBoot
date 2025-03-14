package com.pavle.CRUDproject.services;


import com.pavle.CRUDproject.models.EmployeeReqDTO;
import com.pavle.CRUDproject.models.EmployeeRespDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    EmployeeRespDTO addEmployee(EmployeeReqDTO employeeDTO);
    Page<EmployeeRespDTO> getAllEmployees(int page, int size, String sortBy, String sortDir);
    EmployeeRespDTO findEmployeeById(Long Id);
    void deleteEmployee(Long Id);
    EmployeeRespDTO updateEmployee(Long Id, EmployeeReqDTO employee );
}
