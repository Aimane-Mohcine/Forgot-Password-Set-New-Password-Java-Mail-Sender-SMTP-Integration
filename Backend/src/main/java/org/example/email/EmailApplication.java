package org.example.email;

import org.example.email.Repository.RepositoryUser;
import org.example.email.entites.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication implements CommandLineRunner {
    @Autowired
    RepositoryUser userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Créer un nouvel objet User
        Users user1 = new Users();
        user1.setIdCart(1L);
        user1.setFirstname("ayman");
        user1.setLastname("mohcin");
        user1.setEmail("aimanemohcine2001@gmail.com");
        user1.setCountry("USA");
        user1.setPassword("password123");

        // Insérer l'objet dans la base de données
        userRepository.save(user1);

        // Créer un autre utilisateur
        Users user2 = new Users();
        user2.setIdCart(2L);
        user2.setFirstname("Jane");
        user2.setLastname("Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setCountry("UK");
        user2.setPassword("password456");

        // Insérer l'objet dans la base de données
        userRepository.save(user2);

        System.out.println("Utilisateurs insérés dans la base de données");
    }

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

}
