package com.api_example.service;

import com.api_example.entity.Employee;
import com.api_example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired EmployeeRepository employeeRepository;
    public String saveEmployeeData(Employee employee){
    try {
        employeeRepository.save(employee);
        return "done";
    }catch (Exception e){
        return "failed";
    }
    }

    public void deleteEmployeeId(Long id) {
        employeeRepository.deleteById(id);
    }
}
