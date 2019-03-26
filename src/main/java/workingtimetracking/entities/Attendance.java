package workingtimetracking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {
    @Id
    private int serires;

    @Column(name = "sso_id")
    private String ssoId;

    @Column(name = "time")
    private Date attendanceTime;

    @Column(name = "is_checkin")
    private boolean isCheckin;

    public int getSerires() {
        return serires;
    }

    public void setSerires(int serires) {
        this.serires = serires;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public boolean isCheckin() {
        return isCheckin;
    }

    public void setCheckin(boolean checkin) {
        isCheckin = checkin;
    }
}
