package com.example.sentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employees")
public class EmployeesController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", repository.findAll());
        return "employees/list";
    }

}
