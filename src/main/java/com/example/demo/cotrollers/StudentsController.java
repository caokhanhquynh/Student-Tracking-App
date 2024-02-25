package com.example.demo.cotrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.Student;
import com.example.demo.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentsController {

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/students/view")
    public String getAllStudents(Model model){
        System.out.println("Getting all students");
        //TODO: get all students from database
        List<Student> students = studentRepo.findAll();
        //end of database call
        model.addAttribute("stu", students);
        return "students/display";
    }

    @GetMapping("/")
    public RedirectView process(){
        return new RedirectView("students/view");
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD student");
        String newName = newStudent.get("name");
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height"));
        String newHair = newStudent.get("hair");
        float newGPA = Float.parseFloat(newStudent.get("gpa"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newHair, newGPA));
        response.setStatus(201);
        return "students/sucess";
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam("uid") int uid, HttpServletResponse response) {
        System.out.println("DELETE student");
        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return "students/error";
        }
        studentRepo.deleteById(uid);
        return ("students/sucess");
    }

    @PostMapping("/students/update")
    public String updateStudent(@RequestParam("uid") int uid, @RequestParam Map<String, String> updateStudent, HttpServletResponse response){
        System.out.println("UPDATE student");
        Student student = studentRepo.findByUid(uid);

        if (student == null) {
            return "students/error";
        }

        if (updateStudent.get("name") != "") {
            student.setName(updateStudent.get("name"));
        }
        if (updateStudent.get("weight") != "") {
            student.setWeight(Integer.parseInt(updateStudent.get("weight")));
        }
        if (updateStudent.get("height") != "") {
            student.setHeight(Integer.parseInt(updateStudent.get("height")));
        }
        if (updateStudent.get("hair") != "") {
            student.setHair(updateStudent.get("hair"));
        }
        if (updateStudent.get("gpa") != "") {
            student.setGpa(Float.parseFloat(updateStudent.get("gpa")));
        }
        studentRepo.save(student);

        response.setStatus(HttpServletResponse.SC_OK);

        return "students/sucess";
    }
 }