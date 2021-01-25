/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework2.controllers;

import com.example.Framework2.models.Department;
import com.example.Framework2.repositories.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author User
 */
@Controller
public class MainController {
    @Autowired
    DepartmentRepository departmentRepository;
    
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", new Department());
        return "index";
    }
    
    @PostMapping("/save")
    public String save(Department department){
        departmentRepository.save(department);
        return "redirect:/";
    }
    
    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable("id") String id){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "index";
    }
    
    @GetMapping("/test")
    public String test(){
        System.out.println("Coba cetak");
        
        //Cara insert 1
//        Department d = new Department();
//        d.setId("ADD");
//        d.setName("App Dev");
//        departmentRepository.save(d);
        
        //Cara insert 2
//        Department d1 = new Department("ADD2","App Dev 2");
        departmentRepository.save(new Department("ADD2","App Dev 2"));
              
        //List<Department> departments = departmentRepository.findAll();
        
        departmentRepository.delete(new Department("ADD2"));
        
        for (Department department : departmentRepository.findAll()) {
            System.out.println("Department ID: "+department.getId());
            System.out.println("Department Name: "+department.getName());
        }
        
        return "index";
    }
}
