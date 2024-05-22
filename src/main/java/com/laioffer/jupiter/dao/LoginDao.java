package com.laioffer.jupiter.dao;

import com.laioffer.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LoginDao {//根据id password检查是否match记录
    @Autowired
    private SessionFactory sessionFactory;

    // Verify if the given user Id and password are correct. Returns the user name when it passes
    public String verifyLogin(String userId, String password) {//这里的password已经被加密过
        String name = "";

        try (Session session = sessionFactory.openSession()) { //try with resources
            User user = session.get(User.class, userId);
            if(user != null && user.getPassword().equals((password))) {
                name = user.getFirstName();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally{ //try with resources可以省略这一部分
//            if(session != null) session.close();
//        }
        return name;
    }

}
