package com.devmind.greatreads.controller;

import com.devmind.greatreads.dto.BookDTO;
import com.devmind.greatreads.model.Book;
import com.devmind.greatreads.model.enums.BookStatus;
import com.devmind.greatreads.service.AdministratorService;
import com.devmind.greatreads.service.BookService;
import com.devmind.greatreads.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    private final BookService bookService;
    private final ReviewService reviewService;
    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(BookService bookService,
                                   ReviewService reviewService,
                                   AdministratorService administratorService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.administratorService = administratorService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addNewBook(bookDTO, BookStatus.APPROVED));
    }

    @PostMapping("/update/{bookId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<BookStatus> updatePendingBook(@PathVariable(name = "bookId") Long bookId,
                                                        @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(administratorService.updatePendingStatus(bookId, bookDTO.getStatus()));
    }

    @DeleteMapping("/review/{reviewId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> deleteBookReview(@PathVariable(name = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted");
    }
}
