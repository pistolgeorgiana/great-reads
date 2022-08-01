package com.devmind.greatreads.dto;

import com.devmind.greatreads.model.Reader;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDTO {

    private Integer rating;
    private String comment;
    private Reader reader;
    private LocalDateTime publishedTimestamp;
}
