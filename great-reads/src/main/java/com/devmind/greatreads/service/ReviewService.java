package com.devmind.greatreads.service;

import com.devmind.greatreads.dto.ReviewDTO;
import com.devmind.greatreads.exceptions.RecordNotFoundException;
import com.devmind.greatreads.model.Reader;
import com.devmind.greatreads.model.Review;
import com.devmind.greatreads.model.mappers.ReviewMapper;
import com.devmind.greatreads.repository.ReviewRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReaderService readerService;
    private final ReviewMapper reviewMapper;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Set<Review> getAllByBookId(Long bookId) {
        return reviewRepository.getAllByBook_Id(bookId);
    }

    public Review addNewReview(@NonNull Long readerId, @NonNull ReviewDTO reviewDTO) {
        Reader reader = readerService.findById(readerId);
        Review review = Review.builder()
                .author(reader)
                .comment(reviewDTO.getComment())
                .rating(reviewDTO.getRating())
                .publishedTimestamp(reviewDTO.getPublishedTimestamp())
                .build();
        return reviewRepository.save(review);
    }

    public Review updateReview(@NonNull Long readerId, @NonNull Long reviewId, @NonNull ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new RecordNotFoundException("Review with id " + reviewId + " not found!"));
        reviewMapper.updateReviewFromDto(reviewDTO, review);
        return reviewRepository.save(review);
    }

    public void deleteReview(@NonNull Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
