package com.devmind.greatreads.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "readers_to_books")
public class ReadersToBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "finished")
    private Boolean finished;
}
