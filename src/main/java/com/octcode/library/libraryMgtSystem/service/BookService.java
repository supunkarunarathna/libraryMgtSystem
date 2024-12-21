package com.octcode.library.libraryMgtSystem.service;

import com.octcode.library.libraryMgtSystem.dto.BookDto;
import com.octcode.library.libraryMgtSystem.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.util.List;

public class BookService {

    private ModelMapper modelMapper;
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper){
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    //Register book service
    public BookDto registerBook(BookDto bookDto){

        Book book = this.modelMapper.map(bookDto, Book.class);

        return this.modelMapper.map(bookRepository.saveBook(book), BookDto.class);

    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book borrowBook(String isbnNumber, int userId){

        //First check whether the book is available in the library using isbn number
        List<Book> books = bookRepository.findByIsbnNumber(isbnNumber);

        if(books.isEmpty()){
            throw new BookNotFoundException(isbnNumber);
        }

        //If the book exist, then check the stocks
        Optional<Book> availableBooks = books.stream()
                .filter(book -> (book.getReturn_Date() == null || book.getBorrow_Date() == null) || (book.getReturn_Date() != null))
                .findFirst();

        Book book = availableBooks.orElse(null);

        if(book != null){
            book.setBorrow_Date(new Date());
            book.setBorrower_id(userId);
        }

        return bookRepository.save(book);

    }

    public Book returnBook(int bookId){

        //Get the book details from the DB using bookId to update the return date there.
        Book book = bookRepository.findById((long)bookId).orElseThrow(() -> new BookNotFoundException(String.valueOf(bookId)));

        //Set the return date
        book.setReturn_Date(new Date());
        return bookRepository.save(book);
    }
}
