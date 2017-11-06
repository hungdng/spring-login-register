package hung.ta.springloginregister.controllers;

import hung.ta.springloginregister.entities.CustomUser;
import hung.ta.springloginregister.services.EmailService;
import hung.ta.springloginregister.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

/**
 * @author HUNGTA on 11/04/17 - 3:41 PM
 * @project spring-login-register
 */
@Controller
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Value("${user.exit.in.database}")
    private String MESSAGE_USER_EXIST;

    @Value("${subject.e-mail.register.confirm}")
    private String SUBJECT_EMAIL_CONFIRM;

    @Value("${body.e-mail.register.confirm}")
    private String BODY_EMAIL_CONFIRM;

    @Value("${port.this.server}")
    private String PORT_SERVER;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @GetMapping
    public ModelAndView showRegisterPage(ModelAndView modelAndView, CustomUser customUser){
        modelAndView.addObject("customUser", customUser);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView processRegister(ModelAndView modelAndView, @Valid CustomUser customUser, BindingResult bindingResult, HttpServletRequest request){
        // Lookup user in database by e-mail
        CustomUser userExist = userService.findByEmail(customUser.getEmail());
        if (userExist != null) {
            modelAndView.addObject("errorMessage", MESSAGE_USER_EXIST);
            modelAndView.setViewName("register");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors())
            modelAndView.setViewName("register");
        else {
            // new user so we create user and send confirmation e-mail
            // Disable user until they click on confirmation link in email

            customUser.setEnabled(false);

            // Generate random 36-character string token for confirmation link
            customUser.setConfirmationToken(UUID.randomUUID().toString());
            userService.saveNonPassword(customUser);

            String appUrl = request.getScheme() + "://"+request.getServerName();

            SimpleMailMessage registerMail  = new SimpleMailMessage();
            registerMail.setTo(customUser.getEmail());
            registerMail.setSubject(SUBJECT_EMAIL_CONFIRM);
            registerMail.setText(BODY_EMAIL_CONFIRM + "\n" + appUrl + PORT_SERVER + "/confirm?token=" + customUser.getConfirmationToken());
            registerMail.setFrom(customUser.getEmail());

            emailService.sendEmail(registerMail);
            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + customUser.getEmail());
            modelAndView.addObject("customUser", new CustomUser());
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }
}
