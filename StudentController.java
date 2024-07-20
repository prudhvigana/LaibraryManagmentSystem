package com.backendMarch.librarymanagementsysytem.Controller;

import com.backendMarch.librarymanagementsysytem.DTO.StudentRequestDto;
import com.backendMarch.librarymanagementsysytem.DTO.StudentResponseDto;
import com.backendMarch.librarymanagementsysytem.DTO.StudentUpdateEmailRequestDto;
import com.backendMarch.librarymanagementsysytem.Entity.Student;
import com.backendMarch.librarymanagementsysytem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        studentService.addStudent(studentRequestDto);
        return "Student has been added Successfully";

    }

    @GetMapping("/find_by_email")
    public String findStudentByEmail(@RequestParam("email") String email){

        return studentService.findByEmail(email);
    }



    @PutMapping("/update_email")
    public StudentResponseDto  updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto) {
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }
}
