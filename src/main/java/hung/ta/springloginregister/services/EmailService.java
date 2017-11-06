package hung.ta.springloginregister.services;

import org.springframework.mail.SimpleMailMessage; /**
 * @author HUNGTA on 11/04/17 - 3:44 PM
 * @project spring-login-register
 */
public interface EmailService {
    void sendEmail(SimpleMailMessage mail);
}
