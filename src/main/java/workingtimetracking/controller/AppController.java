package workingtimetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import workingtimetracking.entities.User;
import workingtimetracking.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
    @RequestMapping(value = {"/" }, method = RequestMethod.GET)
    public String goToHome(ModelMap model) {
        model.addAttribute("loggedinuser", userService.getPrincipal());
        return "home";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isCurrentAuthenticationAnonymous = authenticationTrustResolver.isAnonymous(authentication);
        if (isCurrentAuthenticationAnonymous) {
            return "login";
        } else {
            return "redirect:/home";
        }
    }

}
