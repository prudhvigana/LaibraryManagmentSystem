package com.backendMarch.librarymanagementsysytem.Repository;

import com.backendMarch.librarymanagementsysytem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface StudentRepository extends JpaRepository <Student, Integer>{

    Student findByEmail(String email);
    List<Student> findByAge(int age);
}
