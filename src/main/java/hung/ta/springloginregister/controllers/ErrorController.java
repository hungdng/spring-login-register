package hung.ta.springloginregister.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author HUNGTA on 11/04/17 - 10:49 PM
 * @project spring-login-register
 */
@Controller
public class ErrorController {

    @GetMapping("/403")
    public String accessDenied(){
        return "403";
    }
}
