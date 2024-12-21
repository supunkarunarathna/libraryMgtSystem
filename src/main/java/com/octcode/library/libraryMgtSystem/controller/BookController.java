package com.octcode.library.libraryMgtSystem.controller;

import com.octcode.library.libraryMgtSystem.dto.BookDto;
import com.octcode.library.libraryMgtSystem.model.JwtResponse;
import com.octcode.library.libraryMgtSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    public ResponseEntity<BookDto> registerBook(@RequestBody BookDto bookDto){
        BookDto response = bookService.registerBook(bookDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Return all books
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    //Borrow a book
    @PostMapping("/borrow/{isbnNumber}/{userId}")
    public Book borrowBook(@PathVariable String isbnNumber, @PathVariable int userId){

        return bookService.borrowBook(isbnNumber, userId);
    }

    @PostMapping("/return/{bookId}")
    public Book returnBook(@PathVariable int bookId){

        return bookService.returnBook(bookId);

    }
}
