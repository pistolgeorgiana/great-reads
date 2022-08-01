package com.devmind.greatreads.model;

import com.devmind.greatreads.model.enums.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@Table(name = "books")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(mappedBy = "readerBooks", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reader> readerBooks;

    @OneToMany(mappedBy = "reader")
    @JsonIgnore
    private Set<ReadersToBook> readers;

    @ManyToMany(mappedBy = "wishedBooks", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reader> readerWishedBooks;

    @OneToMany(mappedBy = "reader")
    @JsonIgnore
    private Set<ReadersToWishlist> wishlist;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description")
    private String description;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Review> reviews;
}
