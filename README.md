# Use case JPA, Hibernate Spring Data, Many To Many | Users-Roles

Dans cette section, nous allons explorer la mise en place de relations Many-to-Many en utilisant JPA, Hibernate et Spring Web pour développer un système de gestion des utilisateurs. Nous commencerons par configurer le projet avec une base de données H2, puis nous migrerons vers MySQL vers la fin du processus.

## Les dépandances

- Lombok
- H2 Database
- Spring web
- Spring Data JPA

## La conception du projet

![image](https://user-images.githubusercontent.com/48890714/230082393-48d4f6c5-6dbf-4adc-8d3c-ddc784623d30.png)

## Configuration de base de données

H2 est une base de données mémoire pour le test, dans le fichier application properties. Puis nous configurerons notre base de test.

``` properties

spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:users_db
server.port=8080
spring.datasource.username=root
spring.datasource.password=

```

## Les entités

Dans entities ```ma.enset.gestionroleuser.entities```, nous trouverons les entités suivantes :

- ```Role.java```
- ```User.java```

## Les repositories

- ```RoleRepository.java```
- ```UserRepository.java```

## Les services

- ```UserService.java```
- ```UserServiceImpl.java```

## Les controllers

- ```UserController.java```

## Test

Dans la classe ```GestionRoleUserApplication.java``` 

``` java
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
```

Les données peuvent être vérifiées en accédant simplement à ce lien http://localhost:8080/h2-console

## Changer Base de données à Mysql

``` properties
server.port=8080
spring.datasource.username=root
spring.datasource.password=
spring.datasource.url=jdbc:mysql://localhost:3306/Gestion_userRole?createDatabaseIfNotExist=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.jpa.show-sql=true
```
Dans le fichier pom.xml :

``` xml
   <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.32</version>
   </dependency>
```

## Test

Pour vérifier si la migration de H2 vers MySQL s'est déroulée correctement, ainsi que pour confirmer l'exactitude de l'insertion des données dans la base de données, vous pouvez accéder à une URL locale comme suit : ```localhost:8080/users/{username}```, où "username" est un paramètre dynamique. Ceci est rendu possible grâce au code présent dans la classe ```UserController.java```.


``` java
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{username}")
    public User user(@PathVariable String username){
        User user = userService.findUserByUserName(username);
        return  user;
    }
}
```
Ce projet nous a permis d'acquérir une meilleure compréhension de la mise en œuvre des relations Many-to-Many grâce à l'API JPA en Java, ainsi que de nos débuts avec Hibernate. De plus, nous avons exploré la configuration d'un projet Spring pour simplifier le processus de développement pour les développeurs.