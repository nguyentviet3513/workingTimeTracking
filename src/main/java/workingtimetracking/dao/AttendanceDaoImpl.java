package workingtimetracking.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import workingtimetracking.entities.Attendance;

import java.util.Date;

@Repository("attendanceDao")
public class AttendanceDaoImpl extends AbstractDao<Integer, Attendance> implements AttendanceDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void checkin(String ssoId, boolean isCheckin) {
        logger.info("ssoId checkin :",ssoId);
        Attendance attendance = new Attendance();
        attendance.setAttendanceTime(new Date());
        attendance.setSsoId(ssoId);
        attendance.setCheckin(isCheckin);
        persist(attendance);
    }
}
