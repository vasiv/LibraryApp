package pl.kielce.tu.libraryApp.action;

import pl.kielce.tu.libraryApp.model.enumeration.Role;

import java.util.List;

public interface Action {

    void execute();
    String getDisplayName();
    List<Role> getAllowedRoles();
}
