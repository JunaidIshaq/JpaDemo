package org.learning.jpa.repository;


import org.learning.jpa.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> save(User user){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> findById(Integer userId){
        Query query = entityManager.createQuery("select u from User u where u.id = 2");
        User user = (User) query.getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Optional<User> findByName(String userName){
        Query query = entityManager.createQuery("select u from User u where u.name = :userName")
                .setParameter("userName", userName);
        User user = (User) query.getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public List<User> findAll(){
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    public List<User> findAllNamedQuery(){
        Query query = entityManager.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    public Optional<User> findByNameNamedQuery(String name){
        Query query = entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("name", name);
        User user = (User) query.getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }

}
