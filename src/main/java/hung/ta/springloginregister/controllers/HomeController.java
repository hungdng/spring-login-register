package hung.ta.springloginregister.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HUNGTA on 11/04/17 - 10:21 PM
 * @project spring-login-register
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @GetMapping
    public String getHome(){
        return "home";
    }
}
