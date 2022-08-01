package com.devmind.greatreads.service;

import com.devmind.greatreads.dto.BookDTO;
import com.devmind.greatreads.exceptions.RecordNotFoundException;
import com.devmind.greatreads.model.Author;
import com.devmind.greatreads.model.Book;
import com.devmind.greatreads.model.enums.BookStatus;
import com.devmind.greatreads.model.mappers.BookMapper;
import com.devmind.greatreads.repository.AuthorRepository;
import com.devmind.greatreads.repository.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public Book getById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new RecordNotFoundException("No book found with id " + bookId));
    }

    public List<Book> getAll(String category) {
        if (category != null) {
            return bookRepository.findAllByGenre(category);
        }
        return bookRepository.findAll();
    }

    public List<Book> getAllPublishedByGenre(String category) {
        return bookRepository.findAllByGenreAndStatus(category, BookStatus.APPROVED);
    }

    public Set<Book> getAllByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }

    public Book addNewBook(@NonNull BookDTO bookDTO, BookStatus status) {
        Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(
                () -> new RecordNotFoundException("No reader found for id " + bookDTO.getAuthorId()));
        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .description(bookDTO.getDescription())
                .genre(bookDTO.getGenre())
                .status(status)
                .publishedDate(bookDTO.getPublishedDate())
                .author(author)
                .build();
        return bookRepository.save(book);
    }

    public Book updateBook(@NonNull Long bookId, @NonNull BookDTO book) {
        Book bookToUpdate = bookRepository.findByIdAndAuthor_Id(book.getAuthorId(), bookId)
                .orElseThrow(() -> new RecordNotFoundException("Book with id " + bookId + " not found!"));
        bookMapper.updateBookFromDto(book, bookToUpdate);
        return bookRepository.save(bookToUpdate);
    }
}
