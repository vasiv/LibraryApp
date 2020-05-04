package pl.kielce.tu.libraryApp.model;

import pl.kielce.tu.libraryApp.model.enumeration.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ciepluchs
 */
public class User implements Serializable, Comparable<User> {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String personalId;
    private String password;
    private List<Role> roles;

    public User(String firstName, String lastName, String personalId, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.password = password;
        roles = new ArrayList<>();
        roles.add(role);
    }

    @Override
    public int compareTo(User o) {
        if (this.personalId.compareTo(o.personalId) == 0){
            return 0;
        } else {
            return this.lastName.compareTo(lastName);
        }
    }

    public List<Role> getRoles() {
        return roles;
    }
}
