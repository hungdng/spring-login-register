package hung.ta.springloginregister.repositories;

import hung.ta.springloginregister.entities.CustomUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HUNGTA on 11/04/17 - 5:04 PM
 * @project spring-login-register
 */
@Repository
public interface UserRepository extends CrudRepository<CustomUser, Long>{
    CustomUser findByEmail(String email);

    CustomUser findByConfirmationToken(String token);
}
