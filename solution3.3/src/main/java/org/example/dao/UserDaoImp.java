package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = entityManager.createQuery("from User").getResultList();
        return list;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from User where id=" + id).executeUpdate();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
