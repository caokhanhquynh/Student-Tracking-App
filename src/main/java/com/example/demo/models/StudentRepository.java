package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>{
    List<Student> findByWeight(int weight);
    List<Student> findByNameAndHair(String name, String hair);
    Student findByUid(int uid);
}