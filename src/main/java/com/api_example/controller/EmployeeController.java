package com.api_example.controller;

import com.api_example.dto.APIResponse;
import com.api_example.entity.Employee;
import com.api_example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> saveRegistration(@RequestBody Employee employee){
        String status = employeeService.saveEmployeeData(employee);
        APIResponse<String> response = new APIResponse<String>();
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

}
