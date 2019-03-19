package WorkingTimeTracking.service;

public interface LoginService {
    public boolean login(String userName, String password);
    public void forgotPassword(String userName);
}
