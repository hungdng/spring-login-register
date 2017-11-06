package hung.ta.springloginregister.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author HUNGTA on 11/04/17 - 3:19 PM
 * @project spring-login-register
 */
@Entity
@Table(name = "role")
public class Role {
    private Long id;
    private String name;
    private Set<CustomUser> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<CustomUser> getUsers() {
        return users;
    }

    public void setUsers(Set<CustomUser> users) {
        this.users = users;
    }
}
