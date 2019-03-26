package workingtimetracking.dao;

public interface AttendanceDao {
    void checkin(String ssoId,boolean isCheckin);
}
