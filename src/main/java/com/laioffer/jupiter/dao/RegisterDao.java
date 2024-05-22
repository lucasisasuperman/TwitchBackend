package com.laioffer.jupiter.dao;

import com.laioffer.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

//dao data access object, datalayer
//annotation, spring know that object needs to be created

@Repository//ask spring to create dao
public class RegisterDao {
    @Autowired
    private SessionFactory sessionFactory;//sessionfactory连接具体的instance，通过其中定义的方法对数据库操作

    public boolean register(User user){
        Session session = null;

        try {
            session = sessionFactory.openSession();//关系型数据库transaction保证atomic
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();//数据库中逐行执行，若某个操作失败，可以进行回滚
        } catch (PersistenceException | IllegalStateException ex) {//保存的时候发现有问题
            // if hibernate throws this exception, it means the user already be register
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return true;
    }

}
