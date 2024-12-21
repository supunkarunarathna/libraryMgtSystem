package com.octcode.library.libraryMgtSystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {


    private int book_id;

    private String isbn;

    private String book_name;

    private String author;

    private int book_count;
}
