package hung.ta.springloginregister.services;

import hung.ta.springloginregister.entities.CustomUser;

/**
 * @author HUNGTA on 11/04/17 - 3:39 PM
 * @project spring-login-register
 */
public interface UserService {
    CustomUser findByEmail(String email);

    void saveNonPassword(CustomUser customUser);

    void saveWithRoles(CustomUser customUser);

    CustomUser findByConfirmmationToken(String token);
}
