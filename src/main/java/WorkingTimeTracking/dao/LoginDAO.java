package WorkingTimeTracking.dao;

public interface LoginDAO {
    public boolean login(String userName, String password);
    public void forgotPassword(String userName);
}
