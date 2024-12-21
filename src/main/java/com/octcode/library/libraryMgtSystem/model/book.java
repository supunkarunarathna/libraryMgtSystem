package com.octcode.library.libraryMgtSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "book")
public class book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "book_name", nullable = false)
    private String book_name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "return_Date", nullable = false)
    private Date return_Date;

    @Column(name = "borrow_Date", nullable = false)
    private Date borrow_Date;

    @Column(name = "borrower_id", nullable = false)
    private int borrower_id;
}
