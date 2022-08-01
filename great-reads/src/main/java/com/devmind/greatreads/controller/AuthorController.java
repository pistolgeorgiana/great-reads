package com.devmind.greatreads.controller;

import com.devmind.greatreads.dto.BookDTO;
import com.devmind.greatreads.model.Book;
import com.devmind.greatreads.model.enums.BookStatus;
import com.devmind.greatreads.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final BookService bookService;

    @Autowired
    public AuthorController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Author')")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addNewBook(bookDTO, BookStatus.PENDING));
    }
}
