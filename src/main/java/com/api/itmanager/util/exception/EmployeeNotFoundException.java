package com.api.itmanager.util.exception;

import com.api.itmanager.employee.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(Long id) {
        super("Employee not found with ID " + id);
    }
}