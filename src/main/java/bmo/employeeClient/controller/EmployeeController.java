package bmo.employeeClient.controller;

import bmo.employeeClient.dto.Employee;
import bmo.employeeClient.exception.EmployeeNotFoundException;
import bmo.employeeClient.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Employee>> getEmployee(@PathVariable int id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok);
    }
}
