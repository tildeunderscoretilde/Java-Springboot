/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework2.controllers;

import com.example.Framework2.models.Employee;
import com.example.Framework2.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository er;
    
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("employees", er.findAll());
        model.addAttribute("employee", new Employee());
        return "employeeIndex";
    }
    
    @PostMapping("/save")
    public String save(Employee employee){
        er.save(employee);
        return "redirect:/employee/";
    }
    
    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("employees", er.findAll());
        model.addAttribute("employee", er.findById(id).get());
        return "employeeIndex";
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id){
        Employee employee = er.findById(id).get();
        er.delete(employee);
        return "redirect:/employee/";
    }
    
    // hint trainer
//    for (Employee employee : er.findAll()) {
//            System.out.println(employee.getId());           //Menampilkan Id
//            System.out.println(employee.getName());         //Menampilkan Nama
//            System.out.println(employee.getDepartment().getName()); //Menampilkan nama department
//            //jika di thymeleaf menggunakan as.department.name
//        }
//        
//        Employee e = new Employee("14422", "Dev");
//        //Saat Save harus di set departmentnya
//        e.setDepartment(new Department("ADD1"));
//        er.save(e);

}
