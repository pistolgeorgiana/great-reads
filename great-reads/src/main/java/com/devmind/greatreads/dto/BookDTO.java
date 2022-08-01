package com.devmind.greatreads.dto;

import com.devmind.greatreads.model.enums.BookStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookDTO {
    private String title;
    private String genre;
    private String description;
    private Long authorId;
    private BookStatus status;
    private LocalDateTime publishedDate;
}
