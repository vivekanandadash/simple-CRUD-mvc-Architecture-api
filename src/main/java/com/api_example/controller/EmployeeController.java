package com.api_example.controller;

import com.api_example.dto.APIResponse;
import com.api_example.dto.EmployeeDto;
import com.api_example.dto.EmployeeResponse;
import com.api_example.entity.Employee;
import com.api_example.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/v2/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> saveRegistration(
            @Valid @RequestBody Employee employee,
            BindingResult result
    ){
        APIResponse<String> response = new APIResponse<String>();
        if (result.hasErrors()){
           response.setMessage("Invalid Input");
           response.setData(result.getFieldError().getDefaultMessage());
           response.setStatus(400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        String status = employeeService.saveEmployeeData(employee);
        if("done".equals(status)){
            response.setMessage("Transaction Successful");
            response.setData("Done");
            response.setStatus(201);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.setMessage("Transaction Failed");
        response.setData("Failed");
        response.setStatus(409);
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @DeleteMapping("/delete")
    public ResponseEntity<APIResponse<String>> deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployeeId(id);
        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Deleted Successfully");
        response.setData("Done");
        response.setStatus(200);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<EmployeeDto>> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto
    ){
        EmployeeDto dto = employeeService.updateRegistration(id, employeeDto);
        APIResponse<EmployeeDto> response = new APIResponse<>();
        response.setMessage("Update SuccessFully");
        response.setData(dto);
        response.setStatus(200);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<APIResponse<EmployeeResponse>> FindAllEmployeeDetails(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue ="asc",required = false) String sortDir
    ){
        EmployeeResponse employees = employeeService.getAllEmployeeDetails(pageNumber,pageSize,sortBy,sortDir);
        APIResponse<EmployeeResponse> response = new APIResponse<>();
        response.setMessage("get SuccessFully");
        response.setData(employees);
        response.setStatus(200);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //find  by id
    @GetMapping("/find/{id}")
    public ResponseEntity<APIResponse<Employee>> findById(
            @PathVariable Long id
    ){
        Employee employee = employeeService.findByidRegistration(id);

        APIResponse<Employee> response = new APIResponse<>();
        response.setMessage("find Successfully");
        response.setData(employee);
        response.setStatus(200);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }


}
