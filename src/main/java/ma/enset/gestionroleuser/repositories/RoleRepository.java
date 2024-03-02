package ma.enset.gestionroleuser.repositories;

import ma.enset.gestionroleuser.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // component de la couche dao
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}