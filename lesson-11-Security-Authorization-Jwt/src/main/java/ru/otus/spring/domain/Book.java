package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Книга
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false, unique = false)
    private Author author;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", nullable = false, unique = false)
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Review.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<Review> reviews;
}
