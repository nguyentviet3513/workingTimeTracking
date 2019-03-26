package workingtimetracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workingtimetracking.dao.AttendanceDao;

@Service("atttendanceService")
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceDao dao;

    @Override
    public String checkin(String ssoId) {
        if(alreadyCheckin(ssoId)){
            return "Today is checkin already!";
        }
        return "";
    }

    private boolean alreadyCheckin(String ssoId) {

        return false;
    }
}
