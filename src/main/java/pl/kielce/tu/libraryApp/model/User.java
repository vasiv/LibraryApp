package pl.kielce.tu.libraryApp.model;

import pl.kielce.tu.libraryApp.indentity.RandomIdentityManager;
import pl.kielce.tu.libraryApp.model.enumeration.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ciepluchs
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String firstName;
    private String lastName;
    private String personalId;
    private String password;
    private List<Role> roles;

    public User(String firstName, String lastName, String personalId, String password, Role role) {
        id = RandomIdentityManager.generateNewId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.password = password;
        roles = new ArrayList<>();
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }
}
