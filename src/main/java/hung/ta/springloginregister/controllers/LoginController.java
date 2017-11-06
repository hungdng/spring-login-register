package hung.ta.springloginregister.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HUNGTA on 11/04/17 - 10:03 PM
 * @project spring-login-register
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLogin(){
        return "login";
    }
}
