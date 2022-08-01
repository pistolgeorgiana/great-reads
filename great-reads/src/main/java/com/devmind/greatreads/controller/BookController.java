package com.devmind.greatreads.controller;

import com.devmind.greatreads.dto.BookDTO;
import com.devmind.greatreads.model.Book;
import com.devmind.greatreads.model.Review;
import com.devmind.greatreads.service.BookService;
import com.devmind.greatreads.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @Autowired
    public BookController(BookService bookService,
                          ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String category) {
        return ResponseEntity.ok(bookService.getAll(category));
    }

    @GetMapping("/allPublished")
    public ResponseEntity<List<Book>> getAllPublishedBooks(@RequestParam(required = false) String category) {
        return ResponseEntity.ok(bookService.getAllPublishedByGenre(category));
    }

    @GetMapping("/author/{authorId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Author')")
    public ResponseEntity<Set<Book>> getAllAuthorsBook(@PathVariable(name = "authorId") Long authorId) {
        return ResponseEntity.ok(bookService.getAllByAuthorId(authorId));
    }

    @GetMapping("/{bookId}/reviews")
    @PreAuthorize("hasAnyAuthority('Admin', 'Author', 'Reader')")
    public ResponseEntity<Set<Review>> getAllReviesBook(@PathVariable(name = "bookId") Long bookId) {
        return ResponseEntity.ok(reviewService.getAllByBookId(bookId));
    }

    @PostMapping("/{bookId}/update")
    @PreAuthorize("hasAnyAuthority('Admin', 'Author')")
    public ResponseEntity<Book> updateBook(@PathVariable(name = "bookId") Long bookId,
                                           @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookDTO));
    }
}
