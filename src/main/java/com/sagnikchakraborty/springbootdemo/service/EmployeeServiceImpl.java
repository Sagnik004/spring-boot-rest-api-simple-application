package com.sagnikchakraborty.springbootdemo.service;

import com.sagnikchakraborty.springbootdemo.exception.EmployeeNotFoundException;
import com.sagnikchakraborty.springbootdemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        if (employee.getEmployeeId() == null ||
                employee.getEmployeeId().trim().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employees
                .stream()
                .filter(employee -> employee.getEmployeeId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee employeeToDelete = employees
                .stream()
                .filter(employee -> employee.getEmployeeId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        employees.remove(employeeToDelete);
        return "Employee deleted with id: " + id;
    }
}
