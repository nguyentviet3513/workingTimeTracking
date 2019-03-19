package WorkingTimeTracking.service;

import WorkingTimeTracking.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDAO loginDAO;
    @Override
    public boolean login(String userName, String password) {
        return loginDAO.login(userName,password);
    }

    @Override
    public void forgotPassword(String userName) {
        //do Logic forgot password
        loginDAO.forgotPassword(userName);
    }
}
