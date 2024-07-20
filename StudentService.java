package com.backendMarch.librarymanagementsysytem.Service;

import com.backendMarch.librarymanagementsysytem.DTO.StudentRequestDto;
import com.backendMarch.librarymanagementsysytem.DTO.StudentResponseDto;
import com.backendMarch.librarymanagementsysytem.DTO.StudentUpdateEmailRequestDto;
import com.backendMarch.librarymanagementsysytem.Entity.LibraryCard;
import com.backendMarch.librarymanagementsysytem.Entity.Student;
import com.backendMarch.librarymanagementsysytem.Enum.CardStatus;
import com.backendMarch.librarymanagementsysytem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentRequestDto studentRequestDto) {

        //create a student object
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setEmail(studentRequestDto.getEmail());

        //create a card object
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setStudent(student);    // card not set to the student then forein key of card is null

        //set card in student
        student.setCard(card);

        //check
        studentRepository.save(student);
    }

    public String findByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }



    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto) {

        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        // update step
        Student updatedStudent = studentRepository.save(student);


        // convert updated student to response dto
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());

        return studentResponseDto;
    }


}



















