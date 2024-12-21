package com.octcode.library.libraryMgtSystem.repository;

import com.octcode.library.libraryMgtSystem.model.User;
import com.octcode.library.libraryMgtSystem.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserRepository {

    public User save(User user){
        // Save Customer and Orders
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            User newUser = (User) session.save(user);
            transaction.commit();
            return newUser;
        }
        catch (HibernateException he){
            throw he;
        }
    }

    public boolean existsByEmail(String email){
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "SELECT * FROM users WHERE email = :email";
            Query<User> query = session.createNativeQuery(sql, User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            return Objects.nonNull(user);
        }
        catch (HibernateException he){
            throw he;
        }

    }

    public Optional<User> findByUsername(String username){
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "SELECT * FROM user WHERE email = :email";
            Query<User> query = session.createNativeQuery(sql, User.class);
            query.setParameter("email", username);
            User user = query.uniqueResult();
            return Optional.ofNullable(user);
        }
        catch (HibernateException he){
            throw he;
        }
    }

    private Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

}
