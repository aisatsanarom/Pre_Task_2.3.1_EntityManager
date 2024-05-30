package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;

import org.hibernate.query.Query;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
