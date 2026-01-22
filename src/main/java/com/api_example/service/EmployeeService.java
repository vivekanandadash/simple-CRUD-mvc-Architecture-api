package com.api_example.service;

import com.api_example.dto.APIResponse;
import com.api_example.dto.EmployeeDto;
import com.api_example.entity.Employee;
import com.api_example.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public EmployeeDto updateRegistration(Long id , EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setName(employeeDto.getName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setMobile(employeeDto.getMobile());

        Employee saveEmployee = employeeRepository.save(employee);
        BeanUtils.copyProperties(saveEmployee,employeeDto);
        return employeeDto;
    }


    public List<Employee> getEmployeeDetails() {
        List<Employee> emp = employeeRepository.findAll();
        return emp;
    }

    public Employee findByidRegistration(Long id) {
        Employee emp = employeeRepository.findById(id).get();
        return  emp;

    }
}
