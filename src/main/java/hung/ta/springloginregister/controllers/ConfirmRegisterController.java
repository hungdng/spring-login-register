package hung.ta.springloginregister.controllers;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import hung.ta.springloginregister.entities.CustomUser;
import hung.ta.springloginregister.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * @author HUNGTA on 11/04/17 - 8:30 PM
 * @project spring-login-register
 */
@Controller
@RequestMapping("confirm")
public class ConfirmRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${invalid.token}")
    private String MESSAGE_INVALID_TOKEN;
    @Value("${set.password.success}")
    private String MESSAGE_SET_PASSWORD_SUCCESS;


    @GetMapping
    public ModelAndView showConfirmPage(ModelAndView modelAndView, @RequestParam("token") String token) {
        CustomUser user = userService.findByConfirmmationToken(token);
        if (user==null){
            // no token found in DB
            modelAndView.addObject("invalidToken", MESSAGE_INVALID_TOKEN);
        }else{
            // token found
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }
        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView processConfirmForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttributes){

        modelAndView.setViewName("confirm");
        Zxcvbn passwordCheck = new Zxcvbn();
        String pw = requestParams.get("password");
        Strength strength = passwordCheck.measure(pw);

        if (strength.getScore() < 3){
            bindingResult.reject("password");

            redirectAttributes.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            return modelAndView;
        }

        CustomUser user = userService.findByConfirmmationToken(requestParams.get("token"));
        user.setPassword(passwordEncoder.encode(requestParams.get("password")));
        user.setEnabled(true);
        userService.saveWithRoles(user);
        modelAndView.addObject("successMessage", MESSAGE_SET_PASSWORD_SUCCESS);
        return modelAndView;
    }
}
