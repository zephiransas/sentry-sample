package com.example.sentry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employees")
@Slf4j
public class EmployeesController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping
    public String list(Model model) {
        log.warn("employee index is called");
        model.addAttribute("employees", repository.findAll());
        return "employees/list";
    }

    @GetMapping("new")
    public String newEntity(@ModelAttribute("form") EmployeeForm form) {
        return "employees/form";
    }

    @GetMapping("{id:[\\d]+}/edit")
    public String edit(@PathVariable("id") int id,
                       @ModelAttribute("form") EmployeeForm form) {
        Employee employee = repository.findOne(id);
        if(employee == null) return "redirect:/employees";

        form.setId(employee.getId());
        form.setName(employee.getName());
        form.setEmail(employee.getEmail());
        return "employees/form";
    }

    @PostMapping
    public String create(@ModelAttribute("form") EmployeeForm form) {
        Employee employee = Employee.builder()
                .name(form.getName())
                .email(form.getEmail())
                .build();
        repository.save(employee);
        return "redirect:/employees";
    }

    @PutMapping("{id:[\\d]+}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("form") EmployeeForm form) {
        Employee employee = repository.findOne(id);
        employee.setName(form.getName());
        employee.setEmail(form.getEmail());
        repository.save(employee);
        return "redirect:/employees";
    }

}
