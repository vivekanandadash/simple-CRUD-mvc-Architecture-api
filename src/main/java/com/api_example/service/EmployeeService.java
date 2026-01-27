package com.api_example.service;

import com.api_example.dto.APIResponse;
import com.api_example.dto.EmployeeDto;
import com.api_example.dto.EmployeeResponse;
import com.api_example.entity.Employee;
import com.api_example.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired EmployeeRepository employeeRepository;
    public String saveEmployeeData(Employee employee){

        employeeRepository.save(employee);
        return "done";

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


    public EmployeeResponse getAllEmployeeDetails(Integer pageNumber , Integer pageSize) {

        Pageable pages = PageRequest.of(pageNumber,pageSize);

        Page<Employee> pageEmployee = employeeRepository.findAll(pages);
        List<Employee> content = pageEmployee.getContent();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setContent(content);
        employeeResponse.setPageNumber(pageEmployee.getNumber());
        employeeResponse.setPageSize(pageEmployee.getSize());
        employeeResponse.setTotalElements(pageEmployee.getTotalElements());
        employeeResponse.setTotalPages(pageEmployee.getTotalPages());
        employeeResponse.setLastPage(pageEmployee.isLast());

        return employeeResponse;
    }

    public Employee findByidRegistration(Long id) {
        Employee emp = employeeRepository.findById(id).get();
        return  emp;

    }
}
