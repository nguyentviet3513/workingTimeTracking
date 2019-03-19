package WorkingTimeTracking.dao;

import WorkingTimeTracking.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LoginDAOImpl implements LoginDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @Transactional
    public boolean login(String userName, String password) {
        Session session =sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where userName =:userName and password=:password");
        query.setParameter("userName",userName);
        query.setParameter("password",password);
        List<User> listResult = query.list();
        if(listResult != null && !listResult.isEmpty())
            return true;
        return false;
    }

    @Override
    public void forgotPassword(String userName) {

    }
}
