package com.devmind.greatreads.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@Table(name = "reviews")
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Reader author;

    @Column(name = "published_timestamp")
    private LocalDateTime publishedTimestamp;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
