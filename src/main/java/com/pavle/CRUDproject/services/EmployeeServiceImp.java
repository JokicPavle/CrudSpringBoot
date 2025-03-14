package com.pavle.CRUDproject.services;

import com.pavle.CRUDproject.exceptions.EmployeeNotFoundException;
import com.pavle.CRUDproject.models.Employee;
import com.pavle.CRUDproject.models.EmployeeReqDTO;
import com.pavle.CRUDproject.models.EmployeeRespDTO;
import com.pavle.CRUDproject.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImp implements EmployeeService{

   private final EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImp.class);

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeRespDTO addEmployee(EmployeeReqDTO employeeDTO) {
        logger.info("Creating new employee: {}",employeeDTO);
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        logger.info("Successfully created employee with id: {}",savedEmployee.getId());
        return convertToDTO(savedEmployee);
    }
    @Override
    public Page<EmployeeRespDTO> getAllEmployees(int page, int size, String sortBy, String sortDir) {


        Pageable pageable = PageRequest.of(page,size,sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
       Page<Employee> employeePage = employeeRepository.findAll(pageable);
       return employeePage.map(this::convertToDTO);

    }


    @Override
    public EmployeeRespDTO findEmployeeById(Long id) {
        logger.info("Finding employee with id: {}", id);

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        logger.info("Successfully fined employee with id: {}", id);

        return convertToDTO(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with id: {}", id);

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            employeeRepository.deleteById(id);
        logger.info("Successfully deleted employee with id: {}", id);

    }

    @Override
    public EmployeeRespDTO updateEmployee(Long id, EmployeeReqDTO employee) {
        logger.info("Updating employee with id: {}", id);
        Employee editedEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

            editedEmployee.updateEmployeeDTO(employee);
            Employee updatedEmployee = employeeRepository.save(editedEmployee);

        logger.info("Successfully updated employee with id: {}", updatedEmployee.getId());

        return convertToDTO(updatedEmployee);
    }

    public Employee convertToEntity(EmployeeReqDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        return employee;
    }
    public EmployeeRespDTO convertToDTO(Employee employee) {
        return new EmployeeRespDTO(
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getSalary()
                );
    }

}
