package ma.enset.gestionroleuser.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(name = "USER_NAME",unique = true ,length = 20) // index unique au niveau de la base de donnees je ne peux pas trouver un username qui donnee a plusieurs utilisateurs
    private String username;
    private String password;
    @ManyToMany(mappedBy = "users",fetch= FetchType.EAGER) // FetchType.EAGER à chaque fois qu'il charge un user, il va occupper le chargement de leur role aussi
    private List<Role>  roles= new ArrayList<>() ; // utilisation de new array liste, c'est pour eviter nullListe exception dans le cas, on veut associer un role à l'utilisateur
}