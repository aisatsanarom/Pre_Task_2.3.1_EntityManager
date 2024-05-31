package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        EntityManagerFactory emf = entityManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public void saveUser(User user) {
        EntityManagerFactory emf = entityManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(em.contains(user) ? user : em.merge(user));
        em.getTransaction().commit();
    }

    @Override
    public User getUserById(long id) {
        EntityManagerFactory emf = entityManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        EntityManagerFactory emf = entityManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(em.contains(user) ? user : em.merge(user));
        em.getTransaction().commit();
    }
}
