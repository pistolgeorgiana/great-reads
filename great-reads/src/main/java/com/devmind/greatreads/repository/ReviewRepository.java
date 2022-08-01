package com.devmind.greatreads.repository;

import com.devmind.greatreads.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Set<Review> getAllByBook_Id(Long bookId);
}
