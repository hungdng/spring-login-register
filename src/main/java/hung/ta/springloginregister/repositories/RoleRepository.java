package hung.ta.springloginregister.repositories;

import hung.ta.springloginregister.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HUNGTA on 11/04/17 - 5:27 PM
 * @project spring-login-register
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
