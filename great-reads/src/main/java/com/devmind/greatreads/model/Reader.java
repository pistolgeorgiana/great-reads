package com.devmind.greatreads.model;

import com.devmind.greatreads.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(UserRole.Roles.READER)
@NoArgsConstructor
public class Reader extends User {

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "readers_to_books",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> readerBooks;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<ReadersToBook> readBooks;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "readers_to_wishlist",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> wishedBooks;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<ReadersToWishlist> wishlists;
}
