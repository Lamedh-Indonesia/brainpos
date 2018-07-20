package com.brainmatics.pos.app;

import com.brainmatics.pos.core.employee.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRest {

    @GetMapping
    public Employee get() {
        Employee e = new Employee();
        e.setCode("E01");
        e.setName("Michael Suyama");
        return e;
    }
}
