package com.backendMarch.librarymanagementsysytem.Repository;

import com.backendMarch.librarymanagementsysytem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {
}
