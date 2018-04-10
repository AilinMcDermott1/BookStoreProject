package com.ailin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ailin.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
