package ma.enset.gestionroleuser.services;

import ma.enset.gestionroleuser.entities.Role;
import ma.enset.gestionroleuser.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addRole(Role role);
    User findUserByUserName(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username,String roleNAme);

    User Authentification(String username,String password);
}