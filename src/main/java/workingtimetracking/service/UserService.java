package workingtimetracking.service;

import workingtimetracking.entities.Attendance;
import workingtimetracking.entities.User;

import java.util.List;

public interface UserService {
    User findById(int id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);
    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    String getPrincipal();

    List<Attendance> getAllAttendances();

    List<Attendance> getAllAttendances(boolean isCheckin);

    List<Attendance> getMonthAttendances(int month);

}
