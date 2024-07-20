package com.backendMarch.librarymanagementsysytem.DTO;

import com.backendMarch.librarymanagementsysytem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseDto {

    private int id;

    private String name;

    private String email;
}
