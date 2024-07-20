package com.backendMarch.librarymanagementsysytem.DTO;

import com.backendMarch.librarymanagementsysytem.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequestDto {


    private String title;

    private Genre genre;

    private int price;

    private int authorId;
}
