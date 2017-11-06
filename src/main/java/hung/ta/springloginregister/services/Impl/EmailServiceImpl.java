package hung.ta.springloginregister.services.Impl;

import hung.ta.springloginregister.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author HUNGTA on 11/04/17 - 4:18 PM
 * @project spring-login-register
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(SimpleMailMessage mail) {
        javaMailSender.send(mail);
    }
}
