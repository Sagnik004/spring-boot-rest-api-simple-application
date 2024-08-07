package com.sagnikchakraborty.springbootdemo.repository;

import com.sagnikchakraborty.springbootdemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
}
