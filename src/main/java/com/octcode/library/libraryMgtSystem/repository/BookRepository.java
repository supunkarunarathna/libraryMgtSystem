package com.octcode.library.libraryMgtSystem.repository;

import com.octcode.library.libraryMgtSystem.model.User;
import com.octcode.library.libraryMgtSystem.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.awt.print.Book;
import java.util.List;
import java.util.Objects;

public class BookRepository {

    private Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    public Book saveBook(Book book){
        // Save Book
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Book newBook = (Book) session.save(book);
            transaction.commit();
            return newBook;
        }
        catch (HibernateException he){
            throw he;
        }
    }

    public List<Book> findAll(){
        // Find all books
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "";
            Query<Book> query = session.createNativeQuery(sql, Book.class);
            List<Book> books = query.getResultList();
            return books;
        }
        catch (HibernateException he){
            throw he;
        }
        
    }
}
