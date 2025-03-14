package com.pavle.CRUDproject.controllers;

import com.pavle.CRUDproject.models.ApiResponse;
import com.pavle.CRUDproject.models.EmployeeReqDTO;
import com.pavle.CRUDproject.models.EmployeeRespDTO;
import com.pavle.CRUDproject.services.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

        @PostMapping
        public ResponseEntity<ApiResponse<EmployeeRespDTO>> addEmployee(@Valid @RequestBody EmployeeReqDTO employeeDTO) {
            logger.debug("Received request to create employee: {}", employeeDTO);

            EmployeeRespDTO newEmployee = employeeService.addEmployee(employeeDTO);
            ApiResponse<EmployeeRespDTO> response = new ApiResponse<>("Employee added successfully",HttpStatus.OK.value(), newEmployee);
            logger.info("Employee created with id: {}", response.getEmployeeResp().getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<ApiResponse<Page<EmployeeRespDTO>>> getAllEmployees(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "id") String sortBy,
                @RequestParam(defaultValue = "asc") String sortDir
        ) {
            logger.debug("Fetching employees: page={}, size={}, sortBy={}, sortDir={}", page, size, sortBy, sortDir);
            Page<EmployeeRespDTO> employees = employeeService.getAllEmployees(page,size,sortBy,sortDir);
            ApiResponse<Page<EmployeeRespDTO>> response = new ApiResponse<>("All employees fetched",HttpStatus.OK.value(),employees);
            logger.info("Fetched {} employees on page {}", response.getEmployeeResp().getSize(), response.getEmployeeResp().getPageable());
            return ResponseEntity.ok(response);
        }



        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<EmployeeRespDTO>> findEmployeeById(@PathVariable Long id) {
            EmployeeRespDTO employee = employeeService.findEmployeeById(id);
            ApiResponse<EmployeeRespDTO> response = new ApiResponse<>("Employee with id: "+id+" found!",HttpStatus.OK.value(),employee);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<EmployeeRespDTO>> deleteEmployee(@PathVariable Long id) {
            logger.debug("Received request to delete employee with id: {}", id);
            EmployeeRespDTO employee = employeeService.findEmployeeById(id);
            employeeService.deleteEmployee(id);
            ApiResponse<EmployeeRespDTO> response = new ApiResponse<>("Employee deleted successfully",HttpStatus.OK.value(), employee);
            logger.info("Employee deleted with id: {}", id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<EmployeeRespDTO>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeReqDTO employee) {
            logger.debug("Received request to update employee with id: {}", id);
            EmployeeRespDTO updatedEmployee = employeeService.updateEmployee(id, employee);
            ApiResponse<EmployeeRespDTO> response = new ApiResponse<>("Employee updated successfully",HttpStatus.OK.value(), updatedEmployee);
            logger.info("Employee updated with id: {}", id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
}
