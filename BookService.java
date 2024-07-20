package com.backendMarch.librarymanagementsysytem.Service;

import com.backendMarch.librarymanagementsysytem.DTO.BookRequestDto;
import com.backendMarch.librarymanagementsysytem.DTO.BookResponseDto;
import com.backendMarch.librarymanagementsysytem.Entity.Author;
import com.backendMarch.librarymanagementsysytem.Entity.Book;
import com.backendMarch.librarymanagementsysytem.Repository.AuthorRepository;
import com.backendMarch.librarymanagementsysytem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {

        //get the author object
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setPrice(bookRequestDto.getPrice());
        book.setIssued(false);
        book.setAuthor(author);

        author.getBooks().add(book);
        authorRepository.save(author);  // will save both book and author

        //create response also
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;
    }
}
