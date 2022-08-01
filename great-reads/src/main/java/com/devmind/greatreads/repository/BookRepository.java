package com.devmind.greatreads.repository;

import com.devmind.greatreads.model.Book;
import com.devmind.greatreads.model.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByGenreAndStatus(String category, BookStatus approved);

    Set<Book> findAllByAuthor_Id(Long authorId);

    Optional<Book> findByIdAndAuthor_Id(Long bookId, Long authorId);

    List<Book> findAllByGenre(String category);

    List<Book> findAllByStatus(BookStatus pending);
}
