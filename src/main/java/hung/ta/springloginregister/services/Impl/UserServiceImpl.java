package hung.ta.springloginregister.services.Impl;

import hung.ta.springloginregister.entities.CustomUser;
import hung.ta.springloginregister.repositories.RoleRepository;
import hung.ta.springloginregister.repositories.UserRepository;
import hung.ta.springloginregister.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @author HUNGTA on 11/04/17 - 4:18 PM
 * @project spring-login-register
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveNonPassword(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Override
    public void saveWithRoles(CustomUser customUser) {
        customUser.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(customUser);
    }

    @Override
    public CustomUser findByConfirmmationToken(String token) {
        return userRepository.findByConfirmationToken(token);
    }
}
