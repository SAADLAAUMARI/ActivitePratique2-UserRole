package ma.enset.gestionroleuser.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String DESC;
    @Column(unique = true ,length = 20)
    private String roleName;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable (name = "USERS_ROLE") // ce n'est pas obligatoire va faire automatique -> on sait d'avance que la relation manyToMany se transforme dans une table qui relait les deux tables user et role
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users= new ArrayList<>();
}