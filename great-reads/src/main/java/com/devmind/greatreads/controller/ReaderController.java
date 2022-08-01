package com.devmind.greatreads.controller;

import com.devmind.greatreads.dto.ReviewDTO;
import com.devmind.greatreads.service.ReaderService;
import com.devmind.greatreads.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderService readerService;
    private final ReviewService reviewService;

    @Autowired
    public ReaderController(ReaderService readerService,
                            ReviewService reviewService) {
        this.readerService = readerService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{readerId}/book/{bookId}")
    @PreAuthorize("hasAuthority('Reader')")
    public ResponseEntity<?> markBookAsRead(@PathVariable(name = "readerId") Long readerId,
                                            @PathVariable(name = "bookId") Long bookId) {
        return ResponseEntity.ok(readerService.markAsRead(readerId, bookId));
    }

    @GetMapping("/{readerId}/wishlist/{bookId}")
    @PreAuthorize("hasAuthority('Reader')")
    public ResponseEntity<?> addBookToWishlist(@PathVariable(name = "readerId") Long readerId,
                                               @PathVariable(name = "bookId") Long bookId) {
        return ResponseEntity.ok(readerService.addToWishlist(readerId, bookId));
    }

    @PostMapping("/{readerId}/review")
    @PreAuthorize("hasAuthority('Reader')")
    public ResponseEntity<?> addBookNewReview(@PathVariable(name = "readerId") Long readerId,
                                              @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.addNewReview(readerId, reviewDTO));
    }

    @PostMapping("/{readerId}/review/{reviewId}")
    @PreAuthorize("hasAuthority('Reader')")
    public ResponseEntity<?> updateBookReview(@PathVariable(name = "readerId") Long readerId,
                                              @PathVariable(name = "reviewId") Long reviewId,
                                              @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(readerId, reviewId, reviewDTO));
    }
}
