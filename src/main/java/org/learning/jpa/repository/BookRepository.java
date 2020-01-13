package org.learning.jpa.repository;

import org.learning.jpa.model.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookRepository {

    private EntityManager entityManager;

    public BookRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Book> findById(Integer id){
        Book book = entityManager.find(Book.class, id);
        return book != null ? Optional.of(book) : Optional.empty();
    }

    public List<Book> findAll(){
        return entityManager.createQuery("from Book").getResultList();
    }

    public Optional<Book> findByName(String name) {
        Book book = entityManager.createQuery("select b from Book b where b.name = :name", Book.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }

    public Optional<Book> findByNameNamedQuery(String name) {
        Book book = entityManager.createNamedQuery("Book.findByName", Book.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }

    public Optional<Book> save(Book book){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            return Optional.of(book);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
