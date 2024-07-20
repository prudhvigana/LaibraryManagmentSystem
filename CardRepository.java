package com.backendMarch.librarymanagementsysytem.Repository;

import com.backendMarch.librarymanagementsysytem.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard, Integer> {
}
