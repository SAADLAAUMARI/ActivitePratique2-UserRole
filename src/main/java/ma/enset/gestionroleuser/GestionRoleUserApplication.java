package ma.enset.gestionroleuser;

import ma.enset.gestionroleuser.entities.Role;
import ma.enset.gestionroleuser.entities.User;
import ma.enset.gestionroleuser.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionRoleUserApplication  {

    public static void main(String[] args) {

        SpringApplication.run(GestionRoleUserApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean // pour qu'il soit execute au debut de l'application
        // utilisation de lambda
    CommandLineRunner start(UserService userService){
        return args -> {
            User user1= new User();
            user1.setUsername("SaadLaaumari");
            user1.setPassword("123456");
            //userService.addNewUser(user1);

            User user2= new User();
            user2.setUsername("Ghali");
            user2.setPassword("123456");
            //userService.addNewUser(user2);



            Stream.of("student","user","admin").forEach(r->{
                Role role2=new Role();
                role2.setRoleName(r);
                //userService.addRole(role2);
            });


            /*
            userService.addRoleToUser("saad","admin");
            userService.addRoleToUser("Mohamed","user");
            userService.addRoleToUser("Yassine","user");
            userService.addRoleToUser("Laila","student");
            */

            try{
                User user = userService.Authentification("saad","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                user.getRoles().forEach(r->{
                    System.out.println("Role => "+r);
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        };
    }

}