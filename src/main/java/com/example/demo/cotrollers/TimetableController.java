package com.example.demo.cotrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Timetable;
import com.example.demo.models.TimetableRepository;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class TimetableController {

    @Autowired
    private TimetableRepository timetableRepo;

    @PostMapping("/timetable/add")
    public String addTimetable(@RequestParam Map<String, String> newTimetable, HttpServletResponse response){
        System.out.println("ADD timetable");
        int newId = Integer.parseInt(newTimetable.get("studentid"));
        String newDay = newTimetable.get("dayofweek");
        String newCourse = newTimetable.get("coursename");
        timetableRepo.save(new Timetable(newId, newDay, newCourse));
        response.setStatus(201);
        return "students/sucess";
    }
    
    @PostMapping("/timetable/delete")
    public String deleteTimetable(@RequestParam("studentid") int studentid, HttpServletResponse response) {
        System.out.println("DELETE timetable");

        List<Timetable> timetable = timetableRepo.findByStudentid(studentid);
        if (timetable.isEmpty()) {
            return "students/error";
        }
        for (Timetable entry : timetable) {
            timetableRepo.deleteById(entry.getId());
        }
        return "students/sucess";
    }

    @GetMapping("/timetable/view")
    public String getAllStudents(@RequestParam("studentid") int studentid, Model model){
        System.out.println("Getting all students");
        //TODO: get all students from database
        List<Timetable> timetable = timetableRepo.findByStudentid(studentid);
        //end of database call
        model.addAttribute("time", timetable);
        return "students/showTimetable";
    }
}