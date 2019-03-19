package WorkingTimeTracking.controller;

import WorkingTimeTracking.entities.User;
import WorkingTimeTracking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WorkingTimeController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("user", new User());
        return "login-page";
    }
    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user){
        if(user.getUserName() != null && user.getPassWord() != null && loginService.login(user.getUserName(),user.getPassWord())){
           // loginService.login(user.getUserName(),user.getPassWord());
            return "checkin-time-page";
        }
        return ("redirect:///");
    }
}
