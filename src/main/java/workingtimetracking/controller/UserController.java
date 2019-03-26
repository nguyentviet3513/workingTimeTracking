package workingtimetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import workingtimetracking.entities.Attendance;
import workingtimetracking.service.AttendanceService;
import workingtimetracking.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes("roles")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AttendanceService attendanceService;


    @RequestMapping(value = { "/attendance-list-{ssoId}" }, method = RequestMethod.GET)
    public String attendanceList(@PathVariable String ssoId, ModelMap model){
        List<Attendance> attendances = userService.getAllAttendances();
        model.addAttribute("attendances",attendances);
        model.addAttribute("loggedinuser",ssoId);
        return "attendance-list";
    }

    @RequestMapping(value = { "/checkin" }, method = RequestMethod.GET)
    public String checkin(ModelMap model) {
        //check logic

        attendanceService.checkin(userService.getPrincipal());
        return "checkinsuccess";

    }
}
