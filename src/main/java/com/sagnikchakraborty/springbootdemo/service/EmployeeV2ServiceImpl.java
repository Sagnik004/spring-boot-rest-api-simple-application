package com.sagnikchakraborty.springbootdemo.service;

import com.sagnikchakraborty.springbootdemo.entity.EmployeeEntity;
import com.sagnikchakraborty.springbootdemo.exception.EmployeeNotFoundException;
import com.sagnikchakraborty.springbootdemo.model.Employee;
import com.sagnikchakraborty.springbootdemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        // If employeeId not present then create a random ID
        if (employee.getEmployeeId() == null ||
                employee.getEmployeeId().trim().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }

        // Create EmployeeEntity object and copy all properties from
        // Employee over to EmployeeEntity because both have same properties
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, entity);

        employeeRepository.save(entity);

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        List<Employee> employeeList = employeeEntityList
                .stream()
                .map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                })
                .collect(Collectors.toList());

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);

        if (employeeEntity.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity.get(), employee);
        return employee;
    }

    @Override
    public String deleteEmployeeById(String id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);

        if (employeeEntity.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }

        employeeRepository.delete(employeeEntity.get());
        return String.format("Employee deleted with id: %s", id);
    }
}
