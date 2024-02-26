package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    List<Timetable> findByStudentid(int studentid);
    Student findById(int id);
}