package com.devmind.greatreads.service;

import com.devmind.greatreads.exceptions.RecordNotFoundException;
import com.devmind.greatreads.model.User;
import com.devmind.greatreads.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final UserRepository authorRepository;

    public User getById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() ->
                new RecordNotFoundException("No author found for id " + authorId));
    }
}
