package com.pokemon.example.springPokemonExample.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.pokemon.example.springPokemonExample.entities.User;
import com.pokemon.example.springPokemonExample.repositories.UserRepository;

@Component
public class Outputter implements CommandLineRunner {

    private Logger LOG = LoggerFactory.getLogger("Dresseur");

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Checke combien d'objets se trouvent dans la BDD        
        LOG.info("******************");
        LOG.info("Objects in DB : " + userRepository.count());

        // Crée un nouvel utilisateur et l'enregistre dans la BDD
        User user1 = new User("Ondine", "Dresseuse", 19);
        LOG.info("******************");
        LOG.info(user1 + " has been created !");
        userRepository.save(user1);
        LOG.info(user1 + " has been saved !");

        // Crée un second utilisateur et l'enregistre dans la BDD
        User user2 = new User("Sacha", "Dresseur", 33);
        LOG.info("******************");
        LOG.info(user2 + " has been created !");
        userRepository.save(user2);
        LOG.info(user2 + " has been saved !");

         // Lit les informations correspondant au second utilisateur
        User tempUser = userRepository.findById(2L).get(); /* On écrit "2L" car 
                                                              le type de l'id est Long */
        LOG.info("******************");
        LOG.info("Second user's firstName is " + tempUser.getFirstName());
        LOG.info("Second user's lastName is " + tempUser.getLastName());
        LOG.info("Second user's age is " + tempUser.getAge());

        // Liste les utilisateurs enregistrés dans la BDD
        LOG.info("******************");
        LOG.info("Users in list are ");
        for(User myUser : userRepository.findAll()) {
            LOG.info(myUser.toString());
        };

        // Supprime le second utilisateur de la BDD
        userRepository.deleteById(2L); /* risque de provoquer une erreur si 
                                          tu n'as pas vidé ta table avant de relancer 
                                          ton application ! */

        /*  Liste les utilisateurs enregistrés dans la BDD
            (permet de vérifier que le second utilisateur
            a bien été supprimé de la BDD) */
        LOG.info("******************");
        LOG.info("Users in list are ");
        for(User myUser : userRepository.findAll()) {
            LOG.info(myUser.toString());
        };
    }    
}