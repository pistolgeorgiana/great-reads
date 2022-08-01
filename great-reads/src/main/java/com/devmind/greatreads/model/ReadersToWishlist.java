package com.devmind.greatreads.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "readers_to_wishlist")
public class ReadersToWishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
